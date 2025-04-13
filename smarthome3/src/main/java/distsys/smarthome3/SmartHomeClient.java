/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smarthome3;

/**
 *
 * @author macbookm2chip
 */
// gRPC core dependencies
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

// Import generated gRPC classes and messages from the Climate service
import smarthome.climate.Climate.*;
import smarthome.climate.ClimateServiceGrpc;

// Import shared common definitions (like Auth, Device, etc.)
import smarthome.common.Common.*;

// Import generated gRPC classes and messages from the Lighting service
import smarthome.lighting.Lighting.*;
import smarthome.lighting.LightingServiceGrpc;

// Import generated gRPC classes and messages from the Security service
import smarthome.security.Security.*;
import smarthome.security.SecurityServiceGrpc;

// Used for thread synchronization (especially in streaming)
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SmartHomeClient {

    // Server host and port
    private static final String HOST = "localhost";
    private static final int PORT = 50051;

    // Authentication credentials (API key and device ID)
    private static final Auth AUTH = Auth.newBuilder()
            .setApiKey("valid-api-key")
            .setDeviceId("device-thermo-1")
            .build();

    public static void main(String[] args) throws Exception {
        // Step 1: Open a channel to the gRPC server
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext() // No TLS
                .build();

        // ======== CLIMATE SERVICE ========

        // Create stubs for unary and streaming
        ClimateServiceGrpc.ClimateServiceBlockingStub climateStub = ClimateServiceGrpc.newBlockingStub(channel);
        ClimateServiceGrpc.ClimateServiceStub climateAsync = ClimateServiceGrpc.newStub(channel);

        // Unary call to set temperature
        climateStub.setTemperature(SetTemperatureRequest.newBuilder()
                .setAuth(AUTH)
                .setTemperature(24.0f)
                .build());

        // Unary call to get current and target temperature
        TemperatureResponse temp = climateStub.getTemperature(GetTemperatureRequest.newBuilder()
                .setAuth(AUTH)
                .setDevice(Device.newBuilder().setId("device-thermo-1").build())
                .build());
        System.out.println(" Climate => Current: " + temp.getCurrentTemp() + " | Target: " + temp.getTargetTemp());

        // --- Server streaming: climate status updates
        CountDownLatch climateLatch = new CountDownLatch(1);
        climateAsync.streamClimateUpdates(
                ClimateStreamRequest.newBuilder()
                        .setAuth(AUTH)
                        .setDevice(Device.newBuilder().setId("device-thermo-1").build())
                        .setUpdateIntervalSeconds(2)
                        .build(),
                new StreamObserver<ClimateUpdate>() {
                    int count = 0;

                    public void onNext(ClimateUpdate update) {
                        System.out.println(" Climate Update: Temp=" + update.getTemperature() + " Humidity=" + update.getHumidity());
                        if (++count >= 3) climateLatch.countDown();
                    }

                    public void onError(Throwable t) {
                        System.err.println("Error: " + t.getMessage());
                        climateLatch.countDown();
                    }

                    public void onCompleted() {
                        System.out.println(" Climate Streaming Completed.");
                        climateLatch.countDown();
                    }
                });
        climateLatch.await(10, TimeUnit.SECONDS);

        // --- Bi-directional streaming: adjusting temperature
        CountDownLatch adjustLatch = new CountDownLatch(1);
        StreamObserver<TemperatureAdjustment> adjustStream = climateAsync.adjustTemperature(
                new StreamObserver<TemperatureAdjustmentAck>() {
                    public void onNext(TemperatureAdjustmentAck ack) {
                        System.out.println(" Adjust Temp Ack: Target = " + ack.getTargetTemperature());
                    }

                    public void onError(Throwable t) {
                        System.err.println(" Adjust temp error: " + t.getMessage());
                        adjustLatch.countDown();
                    }

                    public void onCompleted() {
                        System.out.println(" Adjust Temperature Completed.");
                        adjustLatch.countDown();
                    }
                });

        // Send adjustments
        adjustStream.onNext(TemperatureAdjustment.newBuilder()
                .setAuth(AUTH)
                .setDevice(Device.newBuilder().setId("device-thermo-1").build())
                .setTemperatureDelta(1.5f).build());

        adjustStream.onNext(TemperatureAdjustment.newBuilder()
                .setAuth(AUTH)
                .setDevice(Device.newBuilder().setId("device-thermo-1").build())
                .setTemperatureDelta(-0.5f).build());

        adjustStream.onCompleted();
        adjustLatch.await(5, TimeUnit.SECONDS);

        
        
        // ======== LIGHTING SERVICE ========

        // Create stubs
        LightingServiceGrpc.LightingServiceBlockingStub lightingStub = LightingServiceGrpc.newBlockingStub(channel);
        LightingServiceGrpc.LightingServiceStub lightingAsync = LightingServiceGrpc.newStub(channel);

        // Toggle the light ON (unary)
        lightingStub.toggleLight(ToggleRequest.newBuilder()
                .setAuth(AUTH)
                .setDevice(Device.newBuilder().setId("device-light-1").build())
                .setOn(true)
                .build());

        // Set brightness to 85%
        lightingStub.setBrightness(BrightnessRequest.newBuilder()
                .setAuth(AUTH)
                .setDevice(Device.newBuilder().setId("device-light-1").build())
                .setPercent(85)
                .build());

        // --- Server streaming: get light status updates
        CountDownLatch lightLatch = new CountDownLatch(1);
        lightingAsync.streamLightStatus(
                LightStreamRequest.newBuilder()
                        .setAuth(AUTH)
                        .setDevice(Device.newBuilder().setId("device-light-1").build())
                        .setUpdateIntervalSeconds(2)
                        .build(),
                new StreamObserver<LightStatus>() {
                    int count = 0;

                    public void onNext(LightStatus status) {
                        System.out.println(" Light Status: On=" + status.getIsOn() + " Brightness=" + status.getBrightness());
                        if (++count >= 3) lightLatch.countDown();
                    }

                    public void onError(Throwable t) {
                        System.err.println(" Light stream error: " + t.getMessage());
                        lightLatch.countDown();
                    }

                    public void onCompleted() {
                        System.out.println(" Light Status Streaming Completed.");
                        lightLatch.countDown();
                    }
                });
        lightLatch.await(10, TimeUnit.SECONDS);

        // --- Client streaming: batch light control commands
        CountDownLatch batchLatch = new CountDownLatch(1);
        StreamObserver<LightControlCommand> batchStream = lightingAsync.batchLightControl(
                new StreamObserver<StatusResponse>() {
                    public void onNext(StatusResponse res) {
                        System.out.println(" Batch Light Result: " + res.getMessage());
                    }

                    public void onError(Throwable t) {
                        System.err.println(" Batch light error: " + t.getMessage());
                        batchLatch.countDown();
                    }

                    public void onCompleted() {
                        System.out.println(" Batch Light Control Completed.");
                        batchLatch.countDown();
                    }
                });

        // Queue multiple commands
        batchStream.onNext(LightControlCommand.newBuilder()
                .setAuth(AUTH)
                .setDevice(Device.newBuilder().setId("device-light-1").build())
                .setToggle(false)
                .build());

        batchStream.onNext(LightControlCommand.newBuilder()
                .setAuth(AUTH)
                .setDevice(Device.newBuilder().setId("device-light-1").build())
                .setSetBrightness(50)
                .build());

        batchStream.onCompleted();
        batchLatch.await(5, TimeUnit.SECONDS);

        
        
        // ======== SECURITY SERVICE ========

        // Create security stubs
        SecurityServiceGrpc.SecurityServiceBlockingStub securityStub = SecurityServiceGrpc.newBlockingStub(channel);
        SecurityServiceGrpc.SecurityServiceStub securityAsync = SecurityServiceGrpc.newStub(channel);

        // Lock the door (unary)
        securityStub.lockDoor(LockRequest.newBuilder()
                .setAuth(AUTH)
                .setDevice(Device.newBuilder().setId("device-door-1").build())
                .build());

        // Unlock the door (unary)
        securityStub.unlockDoor(LockRequest.newBuilder()
                .setAuth(AUTH)
                .setDevice(Device.newBuilder().setId("device-door-1").build())
                .build());

        // --- Server streaming: stream camera feed
        CountDownLatch cameraLatch = new CountDownLatch(1);
        securityAsync.streamCameraFeed(CameraRequest.newBuilder()
                        .setAuth(AUTH)
                        .setCameraId("camera-1")
                        .setFrameRate(2)
                        .build(),
                new StreamObserver<CameraFrame>() {
                    int count = 0;

                    public void onNext(CameraFrame frame) {
                        System.out.println(" Camera Frame Received (timestamp: " + frame.getTimestamp() + ")");
                        if (++count >= 3) cameraLatch.countDown();
                    }

                    public void onError(Throwable t) {
                        System.err.println(" Camera feed error: " + t.getMessage());
                        cameraLatch.countDown();
                    }

                    public void onCompleted() {
                        System.out.println(" Camera Feed Completed.");
                        cameraLatch.countDown();
                    }
                });
        cameraLatch.await(10, TimeUnit.SECONDS);

        // --- Bi-directional streaming: monitor security events
        CountDownLatch securityLatch = new CountDownLatch(1);
        StreamObserver<SecurityEvent> eventStream = securityAsync.monitorSecurityEvents(
                new StreamObserver<SecurityAlert>() {
                    public void onNext(SecurityAlert alert) {
                        System.out.println(" Alert Received: " + alert.getMessage());
                    }

                    public void onError(Throwable t) {
                        System.err.println(" Security monitor error: " + t.getMessage());
                        securityLatch.countDown();
                    }

                    public void onCompleted() {
                        System.out.println(" Security Event Monitoring Completed.");
                        securityLatch.countDown();
                    }
                });

        // Send a motion event
        eventStream.onNext(SecurityEvent.newBuilder()
                .setAuth(AUTH)
                .setMotion(MotionEvent.newBuilder()
                        .setCameraId("camera-1")
                        .setConfidence(0.9f)
                        .build())
                .build());

        // Send an alarm event
        eventStream.onNext(SecurityEvent.newBuilder()
                .setAuth(AUTH)
                .setAlarm(AlarmEvent.newBuilder()
                        .setTriggered(true)
                        .setType(AlarmEvent.AlarmType.INTRUSION)
                        .build())
                .build());

        eventStream.onCompleted();
        securityLatch.await(5, TimeUnit.SECONDS);

        // === Clean up and close channel ===
        channel.shutdownNow();
        System.out.println(" Client completed all operations.");
    }
}
