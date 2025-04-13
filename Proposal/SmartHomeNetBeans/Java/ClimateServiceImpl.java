/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smarthome3;

/**
 *
 * @author macbookm2chip
 */
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import smarthome.climate.*;
import smarthome.climate.Climate.ClimateStreamRequest;
import smarthome.climate.Climate.ClimateUpdate;
import smarthome.climate.Climate.GetTemperatureRequest;
import smarthome.climate.Climate.SetTemperatureRequest;
import smarthome.climate.Climate.TemperatureAdjustment;
import smarthome.climate.Climate.TemperatureAdjustmentAck;
import smarthome.climate.Climate.TemperatureResponse;
import smarthome.common.*;
import smarthome.common.Common.Auth;
import smarthome.common.Common.OperationStatus;
import smarthome.common.Common.StatusResponse;

public class ClimateServiceImpl extends ClimateServiceGrpc.ClimateServiceImplBase{
    
    private final Map<String, ClimateDevice> devices = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public ClimateServiceImpl() {
        // Initialize with some devices
        devices.put("device-thermo-1", new ClimateDevice(22.0f, 22.0f, 45.0f));
        devices.put("device-thermo-2", new ClimateDevice(20.0f, 20.0f, 50.0f));
    }

    @Override
    public void setTemperature(SetTemperatureRequest request, StreamObserver<StatusResponse> responseObserver) {
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        ClimateDevice device = devices.get(request.getAuth().getDeviceId());
        if (device == null) {
            responseObserver.onNext(StatusResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Device not found")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        float temperature = request.getTemperature();
        if (temperature < 10.0f || temperature > 30.0f) {
            responseObserver.onNext(StatusResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Temperature must be between 10.0 and 30.0 degrees")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        device.targetTemperature = temperature;
        responseObserver.onNext(StatusResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Temperature set to " + temperature + "°C")
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void getTemperature(GetTemperatureRequest request, StreamObserver<TemperatureResponse> responseObserver) {
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        ClimateDevice device = devices.get(request.getAuth().getDeviceId());
        if (device == null) {
            responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
            return;
        }

        // Simulate small fluctuations
        device.currentTemperature += (device.targetTemperature - device.currentTemperature) * 0.1f;
        device.currentTemperature += (random.nextFloat() - 0.5f) * 0.2f;
        device.humidity += (random.nextFloat() - 0.5f) * 1.0f;

        responseObserver.onNext(TemperatureResponse.newBuilder()
                .setCurrentTemp(device.currentTemperature)
                .setTargetTemp(device.targetTemperature)
                .setHumidity(device.humidity)
                .build());
        responseObserver.onCompleted();
    }

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

        int interval = Math.max(1, Math.min(request.getUpdateIntervalSeconds(), 60));
        
        try {
            while (!Thread.currentThread().isInterrupted()) {
                device.currentTemperature += (device.targetTemperature - device.currentTemperature) * 0.1f;
                device.currentTemperature += (random.nextFloat() - 0.5f) * 0.2f;
                device.humidity += (random.nextFloat() - 0.5f) * 1.0f;

                ClimateUpdate update = ClimateUpdate.newBuilder()
                        .setTemperature(device.currentTemperature)
                        .setHumidity(device.humidity)
                        .setTimestamp(System.currentTimeMillis())
                        .build();

                responseObserver.onNext(update);
                Thread.sleep(interval * 1000L);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            responseObserver.onCompleted();
        }
    }

    @Override
    public StreamObserver<TemperatureAdjustment> adjustTemperature(StreamObserver<TemperatureAdjustmentAck> responseObserver) {
        return new StreamObserver<TemperatureAdjustment>() {
            @Override
            public void onNext(TemperatureAdjustment request) {
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

                float newTarget = device.targetTemperature + request.getTemperatureDelta();
                OperationStatus status;
                if (newTarget >= 10.0f && newTarget <= 30.0f) {
                    device.targetTemperature = newTarget;
                    status = OperationStatus.SUCCESS;
                } else {
                    status = OperationStatus.INVALID_PARAMETER;
                }

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
                responseObserver.onCompleted();
            }
        };
    }

    private boolean authenticate(Auth auth) {
        // In a real implementation, verify against a database or auth service
        return "valid-api-key".equals(auth.getApiKey()) && auth.getDeviceId().startsWith("device-");
    }

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
    