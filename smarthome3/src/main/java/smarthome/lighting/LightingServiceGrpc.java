package smarthome.lighting;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Defines the gRPC service for controlling smart lighting
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: lighting.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class LightingServiceGrpc {

  private LightingServiceGrpc() {}

  public static final String SERVICE_NAME = "smarthome.lighting.LightingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<smarthome.lighting.Lighting.ToggleRequest,
      smarthome.common.Common.StatusResponse> getToggleLightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ToggleLight",
      requestType = smarthome.lighting.Lighting.ToggleRequest.class,
      responseType = smarthome.common.Common.StatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<smarthome.lighting.Lighting.ToggleRequest,
      smarthome.common.Common.StatusResponse> getToggleLightMethod() {
    io.grpc.MethodDescriptor<smarthome.lighting.Lighting.ToggleRequest, smarthome.common.Common.StatusResponse> getToggleLightMethod;
    if ((getToggleLightMethod = LightingServiceGrpc.getToggleLightMethod) == null) {
      synchronized (LightingServiceGrpc.class) {
        if ((getToggleLightMethod = LightingServiceGrpc.getToggleLightMethod) == null) {
          LightingServiceGrpc.getToggleLightMethod = getToggleLightMethod =
              io.grpc.MethodDescriptor.<smarthome.lighting.Lighting.ToggleRequest, smarthome.common.Common.StatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ToggleLight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.lighting.Lighting.ToggleRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.common.Common.StatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LightingServiceMethodDescriptorSupplier("ToggleLight"))
              .build();
        }
      }
    }
    return getToggleLightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<smarthome.lighting.Lighting.BrightnessRequest,
      smarthome.common.Common.StatusResponse> getSetBrightnessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetBrightness",
      requestType = smarthome.lighting.Lighting.BrightnessRequest.class,
      responseType = smarthome.common.Common.StatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<smarthome.lighting.Lighting.BrightnessRequest,
      smarthome.common.Common.StatusResponse> getSetBrightnessMethod() {
    io.grpc.MethodDescriptor<smarthome.lighting.Lighting.BrightnessRequest, smarthome.common.Common.StatusResponse> getSetBrightnessMethod;
    if ((getSetBrightnessMethod = LightingServiceGrpc.getSetBrightnessMethod) == null) {
      synchronized (LightingServiceGrpc.class) {
        if ((getSetBrightnessMethod = LightingServiceGrpc.getSetBrightnessMethod) == null) {
          LightingServiceGrpc.getSetBrightnessMethod = getSetBrightnessMethod =
              io.grpc.MethodDescriptor.<smarthome.lighting.Lighting.BrightnessRequest, smarthome.common.Common.StatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetBrightness"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.lighting.Lighting.BrightnessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.common.Common.StatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LightingServiceMethodDescriptorSupplier("SetBrightness"))
              .build();
        }
      }
    }
    return getSetBrightnessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<smarthome.lighting.Lighting.LightStreamRequest,
      smarthome.lighting.Lighting.LightStatus> getStreamLightStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamLightStatus",
      requestType = smarthome.lighting.Lighting.LightStreamRequest.class,
      responseType = smarthome.lighting.Lighting.LightStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<smarthome.lighting.Lighting.LightStreamRequest,
      smarthome.lighting.Lighting.LightStatus> getStreamLightStatusMethod() {
    io.grpc.MethodDescriptor<smarthome.lighting.Lighting.LightStreamRequest, smarthome.lighting.Lighting.LightStatus> getStreamLightStatusMethod;
    if ((getStreamLightStatusMethod = LightingServiceGrpc.getStreamLightStatusMethod) == null) {
      synchronized (LightingServiceGrpc.class) {
        if ((getStreamLightStatusMethod = LightingServiceGrpc.getStreamLightStatusMethod) == null) {
          LightingServiceGrpc.getStreamLightStatusMethod = getStreamLightStatusMethod =
              io.grpc.MethodDescriptor.<smarthome.lighting.Lighting.LightStreamRequest, smarthome.lighting.Lighting.LightStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamLightStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.lighting.Lighting.LightStreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.lighting.Lighting.LightStatus.getDefaultInstance()))
              .setSchemaDescriptor(new LightingServiceMethodDescriptorSupplier("StreamLightStatus"))
              .build();
        }
      }
    }
    return getStreamLightStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<smarthome.lighting.Lighting.LightControlCommand,
      smarthome.common.Common.StatusResponse> getBatchLightControlMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BatchLightControl",
      requestType = smarthome.lighting.Lighting.LightControlCommand.class,
      responseType = smarthome.common.Common.StatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<smarthome.lighting.Lighting.LightControlCommand,
      smarthome.common.Common.StatusResponse> getBatchLightControlMethod() {
    io.grpc.MethodDescriptor<smarthome.lighting.Lighting.LightControlCommand, smarthome.common.Common.StatusResponse> getBatchLightControlMethod;
    if ((getBatchLightControlMethod = LightingServiceGrpc.getBatchLightControlMethod) == null) {
      synchronized (LightingServiceGrpc.class) {
        if ((getBatchLightControlMethod = LightingServiceGrpc.getBatchLightControlMethod) == null) {
          LightingServiceGrpc.getBatchLightControlMethod = getBatchLightControlMethod =
              io.grpc.MethodDescriptor.<smarthome.lighting.Lighting.LightControlCommand, smarthome.common.Common.StatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BatchLightControl"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.lighting.Lighting.LightControlCommand.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.common.Common.StatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LightingServiceMethodDescriptorSupplier("BatchLightControl"))
              .build();
        }
      }
    }
    return getBatchLightControlMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LightingServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LightingServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LightingServiceStub>() {
        @java.lang.Override
        public LightingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LightingServiceStub(channel, callOptions);
        }
      };
    return LightingServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LightingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LightingServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LightingServiceBlockingStub>() {
        @java.lang.Override
        public LightingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LightingServiceBlockingStub(channel, callOptions);
        }
      };
    return LightingServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LightingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LightingServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LightingServiceFutureStub>() {
        @java.lang.Override
        public LightingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LightingServiceFutureStub(channel, callOptions);
        }
      };
    return LightingServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Defines the gRPC service for controlling smart lighting
   * </pre>
   */
  public static abstract class LightingServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Unary RPC: Toggles the light ON or OFF
     * </pre>
     */
    public void toggleLight(smarthome.lighting.Lighting.ToggleRequest request,
        io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getToggleLightMethod(), responseObserver);
    }

    /**
     * <pre>
     * Unary RPC: Sets the brightness of the light
     * </pre>
     */
    public void setBrightness(smarthome.lighting.Lighting.BrightnessRequest request,
        io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetBrightnessMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server Streaming RPC: Continuously sends light status updates to the client
     * </pre>
     */
    public void streamLightStatus(smarthome.lighting.Lighting.LightStreamRequest request,
        io.grpc.stub.StreamObserver<smarthome.lighting.Lighting.LightStatus> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamLightStatusMethod(), responseObserver);
    }

    /**
     * <pre>
     * Client Streaming RPC: Accepts a batch of light control commands from the client
     * </pre>
     */
    public io.grpc.stub.StreamObserver<smarthome.lighting.Lighting.LightControlCommand> batchLightControl(
        io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getBatchLightControlMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getToggleLightMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                smarthome.lighting.Lighting.ToggleRequest,
                smarthome.common.Common.StatusResponse>(
                  this, METHODID_TOGGLE_LIGHT)))
          .addMethod(
            getSetBrightnessMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                smarthome.lighting.Lighting.BrightnessRequest,
                smarthome.common.Common.StatusResponse>(
                  this, METHODID_SET_BRIGHTNESS)))
          .addMethod(
            getStreamLightStatusMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                smarthome.lighting.Lighting.LightStreamRequest,
                smarthome.lighting.Lighting.LightStatus>(
                  this, METHODID_STREAM_LIGHT_STATUS)))
          .addMethod(
            getBatchLightControlMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                smarthome.lighting.Lighting.LightControlCommand,
                smarthome.common.Common.StatusResponse>(
                  this, METHODID_BATCH_LIGHT_CONTROL)))
          .build();
    }
  }

  /**
   * <pre>
   * Defines the gRPC service for controlling smart lighting
   * </pre>
   */
  public static final class LightingServiceStub extends io.grpc.stub.AbstractAsyncStub<LightingServiceStub> {
    private LightingServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LightingServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LightingServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC: Toggles the light ON or OFF
     * </pre>
     */
    public void toggleLight(smarthome.lighting.Lighting.ToggleRequest request,
        io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getToggleLightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Unary RPC: Sets the brightness of the light
     * </pre>
     */
    public void setBrightness(smarthome.lighting.Lighting.BrightnessRequest request,
        io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetBrightnessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Server Streaming RPC: Continuously sends light status updates to the client
     * </pre>
     */
    public void streamLightStatus(smarthome.lighting.Lighting.LightStreamRequest request,
        io.grpc.stub.StreamObserver<smarthome.lighting.Lighting.LightStatus> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamLightStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Client Streaming RPC: Accepts a batch of light control commands from the client
     * </pre>
     */
    public io.grpc.stub.StreamObserver<smarthome.lighting.Lighting.LightControlCommand> batchLightControl(
        io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getBatchLightControlMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * Defines the gRPC service for controlling smart lighting
   * </pre>
   */
  public static final class LightingServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<LightingServiceBlockingStub> {
    private LightingServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LightingServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LightingServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC: Toggles the light ON or OFF
     * </pre>
     */
    public smarthome.common.Common.StatusResponse toggleLight(smarthome.lighting.Lighting.ToggleRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getToggleLightMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Unary RPC: Sets the brightness of the light
     * </pre>
     */
    public smarthome.common.Common.StatusResponse setBrightness(smarthome.lighting.Lighting.BrightnessRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetBrightnessMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Server Streaming RPC: Continuously sends light status updates to the client
     * </pre>
     */
    public java.util.Iterator<smarthome.lighting.Lighting.LightStatus> streamLightStatus(
        smarthome.lighting.Lighting.LightStreamRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamLightStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Defines the gRPC service for controlling smart lighting
   * </pre>
   */
  public static final class LightingServiceFutureStub extends io.grpc.stub.AbstractFutureStub<LightingServiceFutureStub> {
    private LightingServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LightingServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LightingServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC: Toggles the light ON or OFF
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<smarthome.common.Common.StatusResponse> toggleLight(
        smarthome.lighting.Lighting.ToggleRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getToggleLightMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Unary RPC: Sets the brightness of the light
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<smarthome.common.Common.StatusResponse> setBrightness(
        smarthome.lighting.Lighting.BrightnessRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetBrightnessMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TOGGLE_LIGHT = 0;
  private static final int METHODID_SET_BRIGHTNESS = 1;
  private static final int METHODID_STREAM_LIGHT_STATUS = 2;
  private static final int METHODID_BATCH_LIGHT_CONTROL = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LightingServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LightingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TOGGLE_LIGHT:
          serviceImpl.toggleLight((smarthome.lighting.Lighting.ToggleRequest) request,
              (io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse>) responseObserver);
          break;
        case METHODID_SET_BRIGHTNESS:
          serviceImpl.setBrightness((smarthome.lighting.Lighting.BrightnessRequest) request,
              (io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse>) responseObserver);
          break;
        case METHODID_STREAM_LIGHT_STATUS:
          serviceImpl.streamLightStatus((smarthome.lighting.Lighting.LightStreamRequest) request,
              (io.grpc.stub.StreamObserver<smarthome.lighting.Lighting.LightStatus>) responseObserver);
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
        case METHODID_BATCH_LIGHT_CONTROL:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.batchLightControl(
              (io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class LightingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LightingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return smarthome.lighting.Lighting.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LightingService");
    }
  }

  private static final class LightingServiceFileDescriptorSupplier
      extends LightingServiceBaseDescriptorSupplier {
    LightingServiceFileDescriptorSupplier() {}
  }

  private static final class LightingServiceMethodDescriptorSupplier
      extends LightingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LightingServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (LightingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LightingServiceFileDescriptorSupplier())
              .addMethod(getToggleLightMethod())
              .addMethod(getSetBrightnessMethod())
              .addMethod(getStreamLightStatusMethod())
              .addMethod(getBatchLightControlMethod())
              .build();
        }
      }
    }
    return result;
  }
}
