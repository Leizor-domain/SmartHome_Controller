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
import smarthome.security.*;
import smarthome.common.*;
import smarthome.common.Common.Auth;
import smarthome.common.Common.StatusResponse;
import smarthome.security.Security.AlarmEvent;
import smarthome.security.Security.CameraFrame;
import smarthome.security.Security.CameraRequest;
import smarthome.security.Security.DetectedObject;
import smarthome.security.Security.DoorEvent;
import smarthome.security.Security.LockRequest;
import smarthome.security.Security.MotionEvent;
import smarthome.security.Security.Rect;
import smarthome.security.Security.SecurityAlert;
import smarthome.security.Security.SecurityEvent;


public class SecurityServiceImpl extends SecurityServiceGrpc.SecurityServiceImplBase {
    private final Map<String, DoorDevice> doors = new ConcurrentHashMap<>();
    private final Map<String, CameraDevice> cameras = new ConcurrentHashMap<>();
    private boolean alarmStatus = false;
    private final Random random = new Random();

    public SecurityServiceImpl() {
        // Initialize with some devices
        doors.put("device-door-1", new DoorDevice(true, "Front Door"));
        doors.put("device-door-2", new DoorDevice(false, "Back Door"));
        
        cameras.put("camera-1", new CameraDevice("Front Yard Camera"));
        cameras.put("camera-2", new CameraDevice("Back Yard Camera"));
    }

    @Override
    public void lockDoor(LockRequest request, StreamObserver<StatusResponse> responseObserver) {
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        DoorDevice door = doors.get(request.getDevice().getId());
        if (door == null) {
            responseObserver.onNext(StatusResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Door not found")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        door.locked = true;
        responseObserver.onNext(StatusResponse.newBuilder()
                .setSuccess(true)
                .setMessage(door.name + " locked")
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void unlockDoor(LockRequest request, StreamObserver<StatusResponse> responseObserver) {
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        DoorDevice door = doors.get(request.getDevice().getId());
        if (door == null) {
            responseObserver.onNext(StatusResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Door not found")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        door.locked = false;
        responseObserver.onNext(StatusResponse.newBuilder()
                .setSuccess(true)
                .setMessage(door.name + " unlocked")
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void streamCameraFeed(CameraRequest request, StreamObserver<CameraFrame> responseObserver) {
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        if (!cameras.containsKey(request.getCameraId())) {
            responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
            return;
        }

        int frameRate = Math.max(1, Math.min(request.getFrameRate(), 30));
        long frameInterval = 1000 / frameRate;
        int frameCount = 0;

        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Simulate frame data
                byte[] frameData = new byte[1024];
                random.nextBytes(frameData);

                // Occasionally add detected objects
                CameraFrame.Builder frameBuilder = CameraFrame.newBuilder()
                        .setImageData(com.google.protobuf.ByteString.copyFrom(frameData))
                        .setTimestamp(System.currentTimeMillis());

                if (frameCount % 30 == 0) {
                    DetectedObject person = DetectedObject.newBuilder()
                            .setType("person")
                            .setConfidence(0.7f + random.nextFloat() * 0.25f)
                            .setBoundingBox(Rect.newBuilder()
                                    .setX(random.nextInt(100))
                                    .setY(random.nextInt(100))
                                    .setWidth(20 + random.nextInt(20))
                                    .setHeight(40 + random.nextInt(20))
                                    .build())
                            .build();
                    frameBuilder.addObjects(person);
                }

                responseObserver.onNext(frameBuilder.build());
                frameCount++;
                Thread.sleep(frameInterval);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            responseObserver.onCompleted();
        }
    }

    @Override
    public StreamObserver<SecurityEvent> monitorSecurityEvents(StreamObserver<SecurityAlert> responseObserver) {
        return new StreamObserver<SecurityEvent>() {
            @Override
            public void onNext(SecurityEvent event) {
                if (!authenticate(event.getAuth())) {
                    return;
                }

                SecurityAlert alert = null;
                
                if (event.hasMotion()) {
                    MotionEvent motion = event.getMotion();
                    if (motion.getConfidence() > 0.8f) {
                        alert = SecurityAlert.newBuilder()
                                .setAlertId("motion-" + System.currentTimeMillis())
                                .setMessage("High confidence motion detected by camera " + motion.getCameraId())
                                .setTimestamp(System.currentTimeMillis())
                                .setEvent(event)
                                .build();
                    }
                } else if (event.hasDoor()) {
                    DoorEvent doorEvent = event.getDoor();
                    DoorDevice door = doors.get(doorEvent.getDoorId());
                    if (door != null && doorEvent.getOpened() && door.locked) {
                        alert = SecurityAlert.newBuilder()
                                .setAlertId("door-" + System.currentTimeMillis())
                                .setMessage("Door " + door.name + " opened while locked!")
                                .setTimestamp(System.currentTimeMillis())
                                .setEvent(event)
                                .build();
                    }
                } else if (event.hasAlarm()) {
                    AlarmEvent alarmEvent = event.getAlarm();
                    if (alarmEvent.getTriggered()) {
                        String alarmType = alarmEvent.getType().name().toLowerCase().replace("_", " ");
                        alert = SecurityAlert.newBuilder()
                                .setAlertId("alarm-" + System.currentTimeMillis())
                                .setMessage(alarmType.substring(0, 1).toUpperCase() + 
                                        alarmType.substring(1) + " alarm triggered!")
                                .setTimestamp(System.currentTimeMillis())
                                .setEvent(event)
                                .build();
                        alarmStatus = true;
                    }
                }

                if (alert != null) {
                    responseObserver.onNext(alert);
                }
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("MonitorSecurityEvents error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

    private boolean authenticate(Auth auth) {
        return "valid-api-key".equals(auth.getApiKey()) && auth.getDeviceId().startsWith("device-");
    }

    private static class DoorDevice {
        boolean locked;
        String name;

        DoorDevice(boolean locked, String name) {
            this.locked = locked;
            this.name = name;
        }
    }

    private static class CameraDevice {
        String name;

        CameraDevice(String name) {
            this.name = name;
        }
    }
}
