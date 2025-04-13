/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smarthome3;

/**
 *
 * @author macbookm2chip
 */
// Import necessary gRPC and protocol buffer classes
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Import generated message types from the lighting and common proto packages
import smarthome.lighting.*;
import smarthome.common.*;
import smarthome.common.Common.Auth;
import smarthome.common.Common.StatusResponse;
import smarthome.lighting.Lighting.*;

// Implementation of the LightingService defined in the lighting.proto
public class LightingServiceImpl extends LightingServiceGrpc.LightingServiceImplBase {

    // Store device state in a thread-safe map
    private final Map<String, LightDevice> devices = new ConcurrentHashMap<>();

    // Constructor to initialize default lighting devices
    public LightingServiceImpl() {
        devices.put("device-light-1", new LightDevice(false, 50));  // Light off, 50% brightness
        devices.put("device-light-2", new LightDevice(true, 75));   // Light on, 75% brightness
    }

    // Handles turning the light ON or OFF (Unary RPC)
    @Override
    public void toggleLight(ToggleRequest request, StreamObserver<StatusResponse> responseObserver) {
        // Authenticate the client
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        // Retrieve device
        LightDevice device = devices.get(request.getDevice().getId());
        if (device == null) {
            responseObserver.onNext(StatusResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Device not found")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        // Apply toggle command
        device.on = request.getOn();

        // Respond with success
        responseObserver.onNext(StatusResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Light " + (device.on ? "on" : "off"))
                .build());
        responseObserver.onCompleted();
    }

    // Handles setting the brightness level (Unary RPC)
    @Override
    public void setBrightness(BrightnessRequest request, StreamObserver<StatusResponse> responseObserver) {
        // Authenticate the request
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        // Find the light device
        LightDevice device = devices.get(request.getDevice().getId());
        if (device == null) {
            responseObserver.onNext(StatusResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Device not found")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        int percent = request.getPercent();

        // Validate brightness range (0-100%)
        if (percent < 0 || percent > 100) {
            responseObserver.onNext(StatusResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Brightness must be between 0 and 100")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        // Set brightness and turn light on if brightness > 0
        device.brightness = percent;
        device.on = percent > 0;

        // Respond with success message
        responseObserver.onNext(StatusResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Brightness set to " + percent + "%")
                .build());
        responseObserver.onCompleted();
    }

    // Streams the current light status at intervals to the client (Server Streaming)
    @Override
    public void streamLightStatus(LightStreamRequest request, StreamObserver<LightStatus> responseObserver) {
        // Check auth
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        LightDevice device = devices.get(request.getDevice().getId());
        if (device == null) {
            responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
            return;
        }

        // Use a capped interval between 1 and 60 seconds
        int interval = Math.max(1, Math.min(request.getUpdateIntervalSeconds(), 60));
        
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Build current light status
                LightStatus status = LightStatus.newBuilder()
                        .setIsOn(device.on)
                        .setBrightness(device.brightness)
                        .setTimestamp(System.currentTimeMillis())
                        .build();

                // Send update to client
                responseObserver.onNext(status);

                // Wait before sending next update
                Thread.sleep(interval * 1000L);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt flag
        } finally {
            responseObserver.onCompleted(); // Finish stream when interrupted
        }
    }

    // Processes a stream of light control commands (Client Streaming)
    @Override
    public StreamObserver<LightControlCommand> batchLightControl(StreamObserver<StatusResponse> responseObserver) {
        return new StreamObserver<LightControlCommand>() {
            int successCount = 0;
            int totalCount = 0;

            @Override
            public void onNext(LightControlCommand request) {
                totalCount++;

                // Authenticate each command
                if (!authenticate(request.getAuth())) return;

                LightDevice device = devices.get(request.getDevice().getId());
                if (device == null) return;

                // Handle toggle command
                if (request.hasToggle()) {
                    device.on = request.getToggle();
                    successCount++;
                }

                // Handle brightness command
                else if (request.hasSetBrightness()) {
                    int brightness = request.getSetBrightness();
                    if (brightness >= 0 && brightness <= 100) {
                        device.brightness = brightness;
                        device.on = brightness > 0;
                        successCount++;
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("BatchLightControl error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                // Respond with summary of batch results
                responseObserver.onNext(StatusResponse.newBuilder()
                        .setSuccess(successCount > 0)
                        .setMessage(String.format("Processed %d/%d commands successfully", successCount, totalCount))
                        .build());
                responseObserver.onCompleted();
            }
        };
    }

    // Basic API key & device format authentication
    private boolean authenticate(Auth auth) {
        return "valid-api-key".equals(auth.getApiKey()) && auth.getDeviceId().startsWith("device-");
    }

    // Represents an in-memory model of a light device
    private static class LightDevice {
        boolean on;         // Whether light is ON
        int brightness;     // Brightness level (0-100)

        LightDevice(boolean on, int brightness) {
            this.on = on;
            this.brightness = brightness;
        }
    }
}