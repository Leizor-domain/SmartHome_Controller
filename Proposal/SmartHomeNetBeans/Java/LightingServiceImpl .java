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
import java.util.concurrent.ConcurrentHashMap;
import smarthome.lighting.*;
import smarthome.common.*;
import smarthome.common.Common.Auth;
import smarthome.common.Common.StatusResponse;
import smarthome.lighting.Lighting.BrightnessRequest;
import smarthome.lighting.Lighting.LightControlCommand;
import smarthome.lighting.Lighting.LightStatus;
import smarthome.lighting.Lighting.LightStreamRequest;
import smarthome.lighting.Lighting.ToggleRequest;

public class LightingServiceImpl extends LightingServiceGrpc.LightingServiceImplBase {
    private final Map<String, LightDevice> devices = new ConcurrentHashMap<>();

    public LightingServiceImpl() {
        // Initialize with some devices
        devices.put("device-light-1", new LightDevice(false, 50));
        devices.put("device-light-2", new LightDevice(true, 75));
    }

    @Override
    public void toggleLight(ToggleRequest request, StreamObserver<StatusResponse> responseObserver) {
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        LightDevice device = devices.get(request.getDevice().getId());
        if (device == null) {
            responseObserver.onNext(StatusResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Device not found")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        device.on = request.getOn();
        responseObserver.onNext(StatusResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Light " + (device.on ? "on" : "off"))
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void setBrightness(BrightnessRequest request, StreamObserver<StatusResponse> responseObserver) {
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

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
        if (percent < 0 || percent > 100) {
            responseObserver.onNext(StatusResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Brightness must be between 0 and 100")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        device.brightness = percent;
        device.on = percent > 0;
        responseObserver.onNext(StatusResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Brightness set to " + percent + "%")
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void streamLightStatus(LightStreamRequest request, StreamObserver<LightStatus> responseObserver) {
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        LightDevice device = devices.get(request.getDevice().getId());
        if (device == null) {
            responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
            return;
        }

        int interval = Math.max(1, Math.min(request.getUpdateIntervalSeconds(), 60));
        
        try {
            while (!Thread.currentThread().isInterrupted()) {
                LightStatus status = LightStatus.newBuilder()
                        .setIsOn(device.on)
                        .setBrightness(device.brightness)
                        .setTimestamp(System.currentTimeMillis())
                        .build();

                responseObserver.onNext(status);
                Thread.sleep(interval * 1000L);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            responseObserver.onCompleted();
        }
    }

    @Override
    public StreamObserver<LightControlCommand> batchLightControl(StreamObserver<StatusResponse> responseObserver) {
        return new StreamObserver<LightControlCommand>() {
            int successCount = 0;
            int totalCount = 0;

            @Override
            public void onNext(LightControlCommand request) {
                totalCount++;
                if (!authenticate(request.getAuth())) {
                    return;
                }

                LightDevice device = devices.get(request.getDevice().getId());
                if (device == null) {
                    return;
                }

                if (request.hasToggle()) {
                    device.on = request.getToggle();
                    successCount++;
                } else if (request.hasSetBrightness()) {
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
                responseObserver.onNext(StatusResponse.newBuilder()
                        .setSuccess(successCount > 0)
                        .setMessage(String.format("Processed %d/%d commands successfully", successCount, totalCount))
                        .build());
                responseObserver.onCompleted();
            }
        };
    }

    private boolean authenticate(Auth auth) {
        return "valid-api-key".equals(auth.getApiKey()) && auth.getDeviceId().startsWith("device-");
    }

    private static class LightDevice {
        boolean on;
        int brightness;

        LightDevice(boolean on, int brightness) {
            this.on = on;
            this.brightness = brightness;
        }
    }
}
