/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smarthome3;

/**
 *
 * @author macbookm2chip
 */
// gRPC status codes and stream handling
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

// Importing Climate service and message classes
import smarthome.climate.*;
import smarthome.climate.Climate.*;

// Importing common message classes shared across services
import smarthome.common.*;
import smarthome.common.Common.*;

// This class provides the implementation of the ClimateService defined in the .proto file
public class ClimateServiceImpl extends ClimateServiceGrpc.ClimateServiceImplBase {

    // Thread-safe map to store devices using their device ID as key
    private final Map<String, ClimateDevice> devices = new ConcurrentHashMap<>();

    // Random generator for simulating sensor readings (temp & humidity changes)
    private final Random random = new Random();

    // Constructor that initializes some demo devices with default values
    public ClimateServiceImpl() {
        devices.put("device-thermo-1", new ClimateDevice(22.0f, 22.0f, 45.0f));
        devices.put("device-thermo-2", new ClimateDevice(20.0f, 20.0f, 50.0f));
    }

    // Unary RPC: Handle setting a new target temperature
    @Override
    public void setTemperature(SetTemperatureRequest request, StreamObserver<StatusResponse> responseObserver) {
        // Check API key and device ID
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        // Get the device by ID
        ClimateDevice device = devices.get(request.getAuth().getDeviceId());
        if (device == null) {
            // Return not found message if device doesn't exist
            responseObserver.onNext(StatusResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Device not found")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        float temperature = request.getTemperature();
        // Validate temperature range (10–30 °C)
        if (temperature < 10.0f || temperature > 30.0f) {
            responseObserver.onNext(StatusResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Temperature must be between 10.0 and 30.0 degrees")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        // Update the device target temperature
        device.targetTemperature = temperature;

        // Respond with success
        responseObserver.onNext(StatusResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Temperature set to " + temperature + "°C")
                .build());
        responseObserver.onCompleted();
    }

    // Unary RPC: Handle client request to get current climate readings
    @Override
    public void getTemperature(GetTemperatureRequest request, StreamObserver<TemperatureResponse> responseObserver) {
        // Authenticate client
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        ClimateDevice device = devices.get(request.getAuth().getDeviceId());
        if (device == null) {
            responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
            return;
        }

        // Simulate temperature & humidity fluctuations
        device.currentTemperature += (device.targetTemperature - device.currentTemperature) * 0.1f;
        device.currentTemperature += (random.nextFloat() - 0.5f) * 0.2f;
        device.humidity += (random.nextFloat() - 0.5f) * 1.0f;

        // Build response and return climate data
        responseObserver.onNext(TemperatureResponse.newBuilder()
                .setCurrentTemp(device.currentTemperature)
                .setTargetTemp(device.targetTemperature)
                .setHumidity(device.humidity)
                .build());
        responseObserver.onCompleted();
    }

    // Server streaming RPC: Sends periodic climate updates
    @Override
    public void streamClimateUpdates(ClimateStreamRequest request, StreamObserver<ClimateUpdate> responseObserver) {
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        ClimateDevice device = devices.get(request.getAuth().getDeviceId());
        if (device == null) {
            responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
            return;
        }

        // Limit interval range to between 1 and 60 seconds
        int interval = Math.max(1, Math.min(request.getUpdateIntervalSeconds(), 60));

        try {
            // Stream updates continuously until thread is interrupted
            while (!Thread.currentThread().isInterrupted()) {
                // Simulate sensor updates
                device.currentTemperature += (device.targetTemperature - device.currentTemperature) * 0.1f;
                device.currentTemperature += (random.nextFloat() - 0.5f) * 0.2f;
                device.humidity += (random.nextFloat() - 0.5f) * 1.0f;

                // Build and send update
                ClimateUpdate update = ClimateUpdate.newBuilder()
                        .setTemperature(device.currentTemperature)
                        .setHumidity(device.humidity)
                        .setTimestamp(System.currentTimeMillis())
                        .build();

                responseObserver.onNext(update);
                Thread.sleep(interval * 1000L);
            }
        } catch (InterruptedException e) {
            // Gracefully exit on interruption
            Thread.currentThread().interrupt();
        } finally {
            // Close the stream
            responseObserver.onCompleted();
        }
    }

    // Bi-directional streaming RPC: Client sends adjustments, server replies with updated state
    @Override
    public StreamObserver<TemperatureAdjustment> adjustTemperature(StreamObserver<TemperatureAdjustmentAck> responseObserver) {
        return new StreamObserver<TemperatureAdjustment>() {

            @Override
            public void onNext(TemperatureAdjustment request) {
                // Authenticate each request
                if (!authenticate(request.getAuth())) {
                    responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
                    return;
                }

                ClimateDevice device = devices.get(request.getAuth().getDeviceId());
                if (device == null) {
                    responseObserver.onNext(TemperatureAdjustmentAck.newBuilder()
                            .setStatus(OperationStatus.DEVICE_NOT_FOUND)
                            .build());
                    return;
                }

                // Adjust target temperature if within valid range
                float newTarget = device.targetTemperature + request.getTemperatureDelta();
                OperationStatus status;
                if (newTarget >= 10.0f && newTarget <= 30.0f) {
                    device.targetTemperature = newTarget;
                    status = OperationStatus.SUCCESS;
                } else {
                    status = OperationStatus.INVALID_PARAMETER;
                }

                // Acknowledge update with current readings
                responseObserver.onNext(TemperatureAdjustmentAck.newBuilder()
                        .setCurrentTemperature(device.currentTemperature)
                        .setTargetTemperature(device.targetTemperature)
                        .setStatus(status)
                        .build());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("AdjustTemperature error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                // Client finished sending adjustments
                responseObserver.onCompleted();
            }
        };
    }

    // Simulates authentication check for API key and device ID
    private boolean authenticate(Auth auth) {
        return "valid-api-key".equals(auth.getApiKey()) && auth.getDeviceId().startsWith("device-");
    }

    // Internal class representing a climate device with state
    private static class ClimateDevice {
        float currentTemperature;
        float targetTemperature;
        float humidity;

        ClimateDevice(float currentTemp, float targetTemp, float humidity) {
            this.currentTemperature = currentTemp;
            this.targetTemperature = targetTemp;
            this.humidity = humidity;
        }
    }
}