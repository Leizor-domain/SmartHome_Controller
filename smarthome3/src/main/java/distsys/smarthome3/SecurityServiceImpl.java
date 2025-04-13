/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smarthome3;

/**
 *
 * @author macbookm2chip
 */
// Import gRPC and protobuf-related classes
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

// Import generated protobuf message types for the security and common packages
import smarthome.security.*;
import smarthome.common.*;
import smarthome.common.Common.Auth;
import smarthome.common.Common.StatusResponse;
import smarthome.security.Security.*;

// gRPC service implementation for the SecurityService
public class SecurityServiceImpl extends SecurityServiceGrpc.SecurityServiceImplBase {

    // Map to store door states using device ID as key
    private final Map<String, DoorDevice> doors = new ConcurrentHashMap<>();

    // Map to store camera details using camera ID as key
    private final Map<String, CameraDevice> cameras = new ConcurrentHashMap<>();

    // Track the alarm status
    private boolean alarmStatus = false;

    // For simulating random detection data and frame contents
    private final Random random = new Random();

    // Constructor initializes demo door and camera devices
    public SecurityServiceImpl() {
        doors.put("device-door-1", new DoorDevice(true, "Front Door"));
        doors.put("device-door-2", new DoorDevice(false, "Back Door"));

        cameras.put("camera-1", new CameraDevice("Front Yard Camera"));
        cameras.put("camera-2", new CameraDevice("Back Yard Camera"));
    }

    // Lock a door using device ID
    @Override
    public void lockDoor(LockRequest request, StreamObserver<StatusResponse> responseObserver) {
        // Authenticate the request
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        // Lookup door by device ID
        DoorDevice door = doors.get(request.getDevice().getId());
        if (door == null) {
            // Respond with error if door not found
            responseObserver.onNext(StatusResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage(" Door not found")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        // Lock the door and respond
        door.locked = true;
        responseObserver.onNext(StatusResponse.newBuilder()
                .setSuccess(true)
                .setMessage(door.name + " locked")
                .build());
        responseObserver.onCompleted();
    }

    // Unlock a door using device ID
    @Override
    public void unlockDoor(LockRequest request, StreamObserver<StatusResponse> responseObserver) {
        // Authenticate the request
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        // Lookup door
        DoorDevice door = doors.get(request.getDevice().getId());
        if (door == null) {
            // Respond if door not found
            responseObserver.onNext(StatusResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage(" Door not found")
                    .build());
            responseObserver.onCompleted();
            return;
        }

        // Unlock and acknowledge
        door.locked = false;
        responseObserver.onNext(StatusResponse.newBuilder()
                .setSuccess(true)
                .setMessage(door.name + " unlocked")
                .build());
        responseObserver.onCompleted();
    }

    // Server streaming method to simulate camera feed
    @Override
    public void streamCameraFeed(CameraRequest request, StreamObserver<CameraFrame> responseObserver) {
        // Validate authentication
        if (!authenticate(request.getAuth())) {
            responseObserver.onError(Status.UNAUTHENTICATED.asRuntimeException());
            return;
        }

        // Validate camera ID
        if (!cameras.containsKey(request.getCameraId())) {
            responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
            return;
        }

        // Determine frame rate and interval
        int frameRate = Math.max(1, Math.min(request.getFrameRate(), 30));
        long frameInterval = 1000 / frameRate;
        int frameCount = 0;

        try {
            // Send frames indefinitely unless interrupted
            while (!Thread.currentThread().isInterrupted()) {
                // Simulate fake image data
                byte[] frameData = new byte[1024];
                random.nextBytes(frameData);

                // Build frame message
                CameraFrame.Builder frameBuilder = CameraFrame.newBuilder()
                        .setImageData(com.google.protobuf.ByteString.copyFrom(frameData))
                        .setTimestamp(System.currentTimeMillis());

                // Occasionally simulate detecting a person in the frame
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

                // Send frame to client
                responseObserver.onNext(frameBuilder.build());
                frameCount++;

                // Wait before sending next frame
                Thread.sleep(frameInterval);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Restore interrupt flag
        } finally {
            responseObserver.onCompleted();  // Complete stream
        }
    }

    // Bidirectional streaming for real-time security event monitoring
    @Override
    public StreamObserver<SecurityEvent> monitorSecurityEvents(StreamObserver<SecurityAlert> responseObserver) {
        return new StreamObserver<SecurityEvent>() {

            @Override
            public void onNext(SecurityEvent event) {
                // Skip if unauthenticated
                if (!authenticate(event.getAuth())) {
                    return;
                }

                SecurityAlert alert = null;

                // Handle motion detection events
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
                }

                // Handle door events (opened while locked)
                else if (event.hasDoor()) {
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
                }

                // Handle alarm triggered events
                else if (event.hasAlarm()) {
                    AlarmEvent alarmEvent = event.getAlarm();
                    if (alarmEvent.getTriggered()) {
                        String alarmType = alarmEvent.getType().name().toLowerCase().replace("_", " ");
                        alert = SecurityAlert.newBuilder()
                                .setAlertId("alarm-" + System.currentTimeMillis())
                                .setMessage(alarmType.substring(0, 1).toUpperCase() + alarmType.substring(1) + " alarm triggered!")
                                .setTimestamp(System.currentTimeMillis())
                                .setEvent(event)
                                .build();
                        alarmStatus = true;
                    }
                }

                // Send alert if generated
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
                responseObserver.onCompleted();  // Notify client that the stream ended
            }
        };
    }

    // Simple authentication method using hardcoded API key
    private boolean authenticate(Auth auth) {
        return "valid-api-key".equals(auth.getApiKey()) && auth.getDeviceId().startsWith("device-");
    }

    // Local class representing a smart door
    private static class DoorDevice {
        boolean locked;
        String name;

        DoorDevice(boolean locked, String name) {
            this.locked = locked;
            this.name = name;
        }
    }

    // Local class representing a smart camera
    private static class CameraDevice {
        String name;

        CameraDevice(String name) {
            this.name = name;
        }
    }
}