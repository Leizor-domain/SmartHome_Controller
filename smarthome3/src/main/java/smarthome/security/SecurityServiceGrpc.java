package smarthome.security;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * gRPC Service: SecurityService
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: security.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SecurityServiceGrpc {

  private SecurityServiceGrpc() {}

  public static final String SERVICE_NAME = "smarthome.security.SecurityService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<smarthome.security.Security.LockRequest,
      smarthome.common.Common.StatusResponse> getLockDoorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LockDoor",
      requestType = smarthome.security.Security.LockRequest.class,
      responseType = smarthome.common.Common.StatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<smarthome.security.Security.LockRequest,
      smarthome.common.Common.StatusResponse> getLockDoorMethod() {
    io.grpc.MethodDescriptor<smarthome.security.Security.LockRequest, smarthome.common.Common.StatusResponse> getLockDoorMethod;
    if ((getLockDoorMethod = SecurityServiceGrpc.getLockDoorMethod) == null) {
      synchronized (SecurityServiceGrpc.class) {
        if ((getLockDoorMethod = SecurityServiceGrpc.getLockDoorMethod) == null) {
          SecurityServiceGrpc.getLockDoorMethod = getLockDoorMethod =
              io.grpc.MethodDescriptor.<smarthome.security.Security.LockRequest, smarthome.common.Common.StatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LockDoor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.security.Security.LockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.common.Common.StatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SecurityServiceMethodDescriptorSupplier("LockDoor"))
              .build();
        }
      }
    }
    return getLockDoorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<smarthome.security.Security.LockRequest,
      smarthome.common.Common.StatusResponse> getUnlockDoorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnlockDoor",
      requestType = smarthome.security.Security.LockRequest.class,
      responseType = smarthome.common.Common.StatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<smarthome.security.Security.LockRequest,
      smarthome.common.Common.StatusResponse> getUnlockDoorMethod() {
    io.grpc.MethodDescriptor<smarthome.security.Security.LockRequest, smarthome.common.Common.StatusResponse> getUnlockDoorMethod;
    if ((getUnlockDoorMethod = SecurityServiceGrpc.getUnlockDoorMethod) == null) {
      synchronized (SecurityServiceGrpc.class) {
        if ((getUnlockDoorMethod = SecurityServiceGrpc.getUnlockDoorMethod) == null) {
          SecurityServiceGrpc.getUnlockDoorMethod = getUnlockDoorMethod =
              io.grpc.MethodDescriptor.<smarthome.security.Security.LockRequest, smarthome.common.Common.StatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnlockDoor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.security.Security.LockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.common.Common.StatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SecurityServiceMethodDescriptorSupplier("UnlockDoor"))
              .build();
        }
      }
    }
    return getUnlockDoorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<smarthome.security.Security.CameraRequest,
      smarthome.security.Security.CameraFrame> getStreamCameraFeedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamCameraFeed",
      requestType = smarthome.security.Security.CameraRequest.class,
      responseType = smarthome.security.Security.CameraFrame.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<smarthome.security.Security.CameraRequest,
      smarthome.security.Security.CameraFrame> getStreamCameraFeedMethod() {
    io.grpc.MethodDescriptor<smarthome.security.Security.CameraRequest, smarthome.security.Security.CameraFrame> getStreamCameraFeedMethod;
    if ((getStreamCameraFeedMethod = SecurityServiceGrpc.getStreamCameraFeedMethod) == null) {
      synchronized (SecurityServiceGrpc.class) {
        if ((getStreamCameraFeedMethod = SecurityServiceGrpc.getStreamCameraFeedMethod) == null) {
          SecurityServiceGrpc.getStreamCameraFeedMethod = getStreamCameraFeedMethod =
              io.grpc.MethodDescriptor.<smarthome.security.Security.CameraRequest, smarthome.security.Security.CameraFrame>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamCameraFeed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.security.Security.CameraRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.security.Security.CameraFrame.getDefaultInstance()))
              .setSchemaDescriptor(new SecurityServiceMethodDescriptorSupplier("StreamCameraFeed"))
              .build();
        }
      }
    }
    return getStreamCameraFeedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<smarthome.security.Security.SecurityEvent,
      smarthome.security.Security.SecurityAlert> getMonitorSecurityEventsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MonitorSecurityEvents",
      requestType = smarthome.security.Security.SecurityEvent.class,
      responseType = smarthome.security.Security.SecurityAlert.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<smarthome.security.Security.SecurityEvent,
      smarthome.security.Security.SecurityAlert> getMonitorSecurityEventsMethod() {
    io.grpc.MethodDescriptor<smarthome.security.Security.SecurityEvent, smarthome.security.Security.SecurityAlert> getMonitorSecurityEventsMethod;
    if ((getMonitorSecurityEventsMethod = SecurityServiceGrpc.getMonitorSecurityEventsMethod) == null) {
      synchronized (SecurityServiceGrpc.class) {
        if ((getMonitorSecurityEventsMethod = SecurityServiceGrpc.getMonitorSecurityEventsMethod) == null) {
          SecurityServiceGrpc.getMonitorSecurityEventsMethod = getMonitorSecurityEventsMethod =
              io.grpc.MethodDescriptor.<smarthome.security.Security.SecurityEvent, smarthome.security.Security.SecurityAlert>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MonitorSecurityEvents"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.security.Security.SecurityEvent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.security.Security.SecurityAlert.getDefaultInstance()))
              .setSchemaDescriptor(new SecurityServiceMethodDescriptorSupplier("MonitorSecurityEvents"))
              .build();
        }
      }
    }
    return getMonitorSecurityEventsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SecurityServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SecurityServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SecurityServiceStub>() {
        @java.lang.Override
        public SecurityServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SecurityServiceStub(channel, callOptions);
        }
      };
    return SecurityServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SecurityServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SecurityServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SecurityServiceBlockingStub>() {
        @java.lang.Override
        public SecurityServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SecurityServiceBlockingStub(channel, callOptions);
        }
      };
    return SecurityServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SecurityServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SecurityServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SecurityServiceFutureStub>() {
        @java.lang.Override
        public SecurityServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SecurityServiceFutureStub(channel, callOptions);
        }
      };
    return SecurityServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * gRPC Service: SecurityService
   * </pre>
   */
  public static abstract class SecurityServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Unary RPC to lock a door device
     * </pre>
     */
    public void lockDoor(smarthome.security.Security.LockRequest request,
        io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLockDoorMethod(), responseObserver);
    }

    /**
     * <pre>
     * Unary RPC to unlock a door device
     * </pre>
     */
    public void unlockDoor(smarthome.security.Security.LockRequest request,
        io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnlockDoorMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server Streaming RPC to stream live camera feed frames to the client
     * </pre>
     */
    public void streamCameraFeed(smarthome.security.Security.CameraRequest request,
        io.grpc.stub.StreamObserver<smarthome.security.Security.CameraFrame> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamCameraFeedMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bidirectional Streaming RPC to monitor various security-related events and receive alerts
     * </pre>
     */
    public io.grpc.stub.StreamObserver<smarthome.security.Security.SecurityEvent> monitorSecurityEvents(
        io.grpc.stub.StreamObserver<smarthome.security.Security.SecurityAlert> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getMonitorSecurityEventsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLockDoorMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                smarthome.security.Security.LockRequest,
                smarthome.common.Common.StatusResponse>(
                  this, METHODID_LOCK_DOOR)))
          .addMethod(
            getUnlockDoorMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                smarthome.security.Security.LockRequest,
                smarthome.common.Common.StatusResponse>(
                  this, METHODID_UNLOCK_DOOR)))
          .addMethod(
            getStreamCameraFeedMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                smarthome.security.Security.CameraRequest,
                smarthome.security.Security.CameraFrame>(
                  this, METHODID_STREAM_CAMERA_FEED)))
          .addMethod(
            getMonitorSecurityEventsMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                smarthome.security.Security.SecurityEvent,
                smarthome.security.Security.SecurityAlert>(
                  this, METHODID_MONITOR_SECURITY_EVENTS)))
          .build();
    }
  }

  /**
   * <pre>
   * gRPC Service: SecurityService
   * </pre>
   */
  public static final class SecurityServiceStub extends io.grpc.stub.AbstractAsyncStub<SecurityServiceStub> {
    private SecurityServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SecurityServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SecurityServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC to lock a door device
     * </pre>
     */
    public void lockDoor(smarthome.security.Security.LockRequest request,
        io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLockDoorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Unary RPC to unlock a door device
     * </pre>
     */
    public void unlockDoor(smarthome.security.Security.LockRequest request,
        io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnlockDoorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Server Streaming RPC to stream live camera feed frames to the client
     * </pre>
     */
    public void streamCameraFeed(smarthome.security.Security.CameraRequest request,
        io.grpc.stub.StreamObserver<smarthome.security.Security.CameraFrame> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamCameraFeedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Bidirectional Streaming RPC to monitor various security-related events and receive alerts
     * </pre>
     */
    public io.grpc.stub.StreamObserver<smarthome.security.Security.SecurityEvent> monitorSecurityEvents(
        io.grpc.stub.StreamObserver<smarthome.security.Security.SecurityAlert> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getMonitorSecurityEventsMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * gRPC Service: SecurityService
   * </pre>
   */
  public static final class SecurityServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<SecurityServiceBlockingStub> {
    private SecurityServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SecurityServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SecurityServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC to lock a door device
     * </pre>
     */
    public smarthome.common.Common.StatusResponse lockDoor(smarthome.security.Security.LockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLockDoorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Unary RPC to unlock a door device
     * </pre>
     */
    public smarthome.common.Common.StatusResponse unlockDoor(smarthome.security.Security.LockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnlockDoorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Server Streaming RPC to stream live camera feed frames to the client
     * </pre>
     */
    public java.util.Iterator<smarthome.security.Security.CameraFrame> streamCameraFeed(
        smarthome.security.Security.CameraRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamCameraFeedMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * gRPC Service: SecurityService
   * </pre>
   */
  public static final class SecurityServiceFutureStub extends io.grpc.stub.AbstractFutureStub<SecurityServiceFutureStub> {
    private SecurityServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SecurityServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SecurityServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC to lock a door device
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<smarthome.common.Common.StatusResponse> lockDoor(
        smarthome.security.Security.LockRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLockDoorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Unary RPC to unlock a door device
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<smarthome.common.Common.StatusResponse> unlockDoor(
        smarthome.security.Security.LockRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnlockDoorMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOCK_DOOR = 0;
  private static final int METHODID_UNLOCK_DOOR = 1;
  private static final int METHODID_STREAM_CAMERA_FEED = 2;
  private static final int METHODID_MONITOR_SECURITY_EVENTS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SecurityServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SecurityServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOCK_DOOR:
          serviceImpl.lockDoor((smarthome.security.Security.LockRequest) request,
              (io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse>) responseObserver);
          break;
        case METHODID_UNLOCK_DOOR:
          serviceImpl.unlockDoor((smarthome.security.Security.LockRequest) request,
              (io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse>) responseObserver);
          break;
        case METHODID_STREAM_CAMERA_FEED:
          serviceImpl.streamCameraFeed((smarthome.security.Security.CameraRequest) request,
              (io.grpc.stub.StreamObserver<smarthome.security.Security.CameraFrame>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MONITOR_SECURITY_EVENTS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.monitorSecurityEvents(
              (io.grpc.stub.StreamObserver<smarthome.security.Security.SecurityAlert>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SecurityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SecurityServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return smarthome.security.Security.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SecurityService");
    }
  }

  private static final class SecurityServiceFileDescriptorSupplier
      extends SecurityServiceBaseDescriptorSupplier {
    SecurityServiceFileDescriptorSupplier() {}
  }

  private static final class SecurityServiceMethodDescriptorSupplier
      extends SecurityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SecurityServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SecurityServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SecurityServiceFileDescriptorSupplier())
              .addMethod(getLockDoorMethod())
              .addMethod(getUnlockDoorMethod())
              .addMethod(getStreamCameraFeedMethod())
              .addMethod(getMonitorSecurityEventsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
