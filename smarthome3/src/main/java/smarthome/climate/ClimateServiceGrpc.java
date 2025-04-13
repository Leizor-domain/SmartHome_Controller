package smarthome.climate;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * ClimateService defines all gRPC methods for managing and monitoring climate systems
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: climate.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ClimateServiceGrpc {

  private ClimateServiceGrpc() {}

  public static final String SERVICE_NAME = "smarthome.climate.ClimateService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<smarthome.climate.Climate.SetTemperatureRequest,
      smarthome.common.Common.StatusResponse> getSetTemperatureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetTemperature",
      requestType = smarthome.climate.Climate.SetTemperatureRequest.class,
      responseType = smarthome.common.Common.StatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<smarthome.climate.Climate.SetTemperatureRequest,
      smarthome.common.Common.StatusResponse> getSetTemperatureMethod() {
    io.grpc.MethodDescriptor<smarthome.climate.Climate.SetTemperatureRequest, smarthome.common.Common.StatusResponse> getSetTemperatureMethod;
    if ((getSetTemperatureMethod = ClimateServiceGrpc.getSetTemperatureMethod) == null) {
      synchronized (ClimateServiceGrpc.class) {
        if ((getSetTemperatureMethod = ClimateServiceGrpc.getSetTemperatureMethod) == null) {
          ClimateServiceGrpc.getSetTemperatureMethod = getSetTemperatureMethod =
              io.grpc.MethodDescriptor.<smarthome.climate.Climate.SetTemperatureRequest, smarthome.common.Common.StatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetTemperature"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.climate.Climate.SetTemperatureRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.common.Common.StatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ClimateServiceMethodDescriptorSupplier("SetTemperature"))
              .build();
        }
      }
    }
    return getSetTemperatureMethod;
  }

  private static volatile io.grpc.MethodDescriptor<smarthome.climate.Climate.GetTemperatureRequest,
      smarthome.climate.Climate.TemperatureResponse> getGetTemperatureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTemperature",
      requestType = smarthome.climate.Climate.GetTemperatureRequest.class,
      responseType = smarthome.climate.Climate.TemperatureResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<smarthome.climate.Climate.GetTemperatureRequest,
      smarthome.climate.Climate.TemperatureResponse> getGetTemperatureMethod() {
    io.grpc.MethodDescriptor<smarthome.climate.Climate.GetTemperatureRequest, smarthome.climate.Climate.TemperatureResponse> getGetTemperatureMethod;
    if ((getGetTemperatureMethod = ClimateServiceGrpc.getGetTemperatureMethod) == null) {
      synchronized (ClimateServiceGrpc.class) {
        if ((getGetTemperatureMethod = ClimateServiceGrpc.getGetTemperatureMethod) == null) {
          ClimateServiceGrpc.getGetTemperatureMethod = getGetTemperatureMethod =
              io.grpc.MethodDescriptor.<smarthome.climate.Climate.GetTemperatureRequest, smarthome.climate.Climate.TemperatureResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTemperature"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.climate.Climate.GetTemperatureRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.climate.Climate.TemperatureResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ClimateServiceMethodDescriptorSupplier("GetTemperature"))
              .build();
        }
      }
    }
    return getGetTemperatureMethod;
  }

  private static volatile io.grpc.MethodDescriptor<smarthome.climate.Climate.ClimateStreamRequest,
      smarthome.climate.Climate.ClimateUpdate> getStreamClimateUpdatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamClimateUpdates",
      requestType = smarthome.climate.Climate.ClimateStreamRequest.class,
      responseType = smarthome.climate.Climate.ClimateUpdate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<smarthome.climate.Climate.ClimateStreamRequest,
      smarthome.climate.Climate.ClimateUpdate> getStreamClimateUpdatesMethod() {
    io.grpc.MethodDescriptor<smarthome.climate.Climate.ClimateStreamRequest, smarthome.climate.Climate.ClimateUpdate> getStreamClimateUpdatesMethod;
    if ((getStreamClimateUpdatesMethod = ClimateServiceGrpc.getStreamClimateUpdatesMethod) == null) {
      synchronized (ClimateServiceGrpc.class) {
        if ((getStreamClimateUpdatesMethod = ClimateServiceGrpc.getStreamClimateUpdatesMethod) == null) {
          ClimateServiceGrpc.getStreamClimateUpdatesMethod = getStreamClimateUpdatesMethod =
              io.grpc.MethodDescriptor.<smarthome.climate.Climate.ClimateStreamRequest, smarthome.climate.Climate.ClimateUpdate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamClimateUpdates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.climate.Climate.ClimateStreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.climate.Climate.ClimateUpdate.getDefaultInstance()))
              .setSchemaDescriptor(new ClimateServiceMethodDescriptorSupplier("StreamClimateUpdates"))
              .build();
        }
      }
    }
    return getStreamClimateUpdatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<smarthome.climate.Climate.TemperatureAdjustment,
      smarthome.climate.Climate.TemperatureAdjustmentAck> getAdjustTemperatureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AdjustTemperature",
      requestType = smarthome.climate.Climate.TemperatureAdjustment.class,
      responseType = smarthome.climate.Climate.TemperatureAdjustmentAck.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<smarthome.climate.Climate.TemperatureAdjustment,
      smarthome.climate.Climate.TemperatureAdjustmentAck> getAdjustTemperatureMethod() {
    io.grpc.MethodDescriptor<smarthome.climate.Climate.TemperatureAdjustment, smarthome.climate.Climate.TemperatureAdjustmentAck> getAdjustTemperatureMethod;
    if ((getAdjustTemperatureMethod = ClimateServiceGrpc.getAdjustTemperatureMethod) == null) {
      synchronized (ClimateServiceGrpc.class) {
        if ((getAdjustTemperatureMethod = ClimateServiceGrpc.getAdjustTemperatureMethod) == null) {
          ClimateServiceGrpc.getAdjustTemperatureMethod = getAdjustTemperatureMethod =
              io.grpc.MethodDescriptor.<smarthome.climate.Climate.TemperatureAdjustment, smarthome.climate.Climate.TemperatureAdjustmentAck>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AdjustTemperature"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.climate.Climate.TemperatureAdjustment.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  smarthome.climate.Climate.TemperatureAdjustmentAck.getDefaultInstance()))
              .setSchemaDescriptor(new ClimateServiceMethodDescriptorSupplier("AdjustTemperature"))
              .build();
        }
      }
    }
    return getAdjustTemperatureMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClimateServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClimateServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClimateServiceStub>() {
        @java.lang.Override
        public ClimateServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClimateServiceStub(channel, callOptions);
        }
      };
    return ClimateServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClimateServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClimateServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClimateServiceBlockingStub>() {
        @java.lang.Override
        public ClimateServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClimateServiceBlockingStub(channel, callOptions);
        }
      };
    return ClimateServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClimateServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClimateServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClimateServiceFutureStub>() {
        @java.lang.Override
        public ClimateServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClimateServiceFutureStub(channel, callOptions);
        }
      };
    return ClimateServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * ClimateService defines all gRPC methods for managing and monitoring climate systems
   * </pre>
   */
  public static abstract class ClimateServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Unary RPC: Client sends a temperature to set, server responds with a success status
     * </pre>
     */
    public void setTemperature(smarthome.climate.Climate.SetTemperatureRequest request,
        io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetTemperatureMethod(), responseObserver);
    }

    /**
     * <pre>
     * Unary RPC: Client requests current climate data (temperature/humidity)
     * </pre>
     */
    public void getTemperature(smarthome.climate.Climate.GetTemperatureRequest request,
        io.grpc.stub.StreamObserver<smarthome.climate.Climate.TemperatureResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTemperatureMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server Streaming RPC: Server continuously streams climate updates at intervals
     * </pre>
     */
    public void streamClimateUpdates(smarthome.climate.Climate.ClimateStreamRequest request,
        io.grpc.stub.StreamObserver<smarthome.climate.Climate.ClimateUpdate> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamClimateUpdatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bi-directional Streaming RPC: Client and server stream temperature adjustments and acknowledgements
     * </pre>
     */
    public io.grpc.stub.StreamObserver<smarthome.climate.Climate.TemperatureAdjustment> adjustTemperature(
        io.grpc.stub.StreamObserver<smarthome.climate.Climate.TemperatureAdjustmentAck> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getAdjustTemperatureMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetTemperatureMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                smarthome.climate.Climate.SetTemperatureRequest,
                smarthome.common.Common.StatusResponse>(
                  this, METHODID_SET_TEMPERATURE)))
          .addMethod(
            getGetTemperatureMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                smarthome.climate.Climate.GetTemperatureRequest,
                smarthome.climate.Climate.TemperatureResponse>(
                  this, METHODID_GET_TEMPERATURE)))
          .addMethod(
            getStreamClimateUpdatesMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                smarthome.climate.Climate.ClimateStreamRequest,
                smarthome.climate.Climate.ClimateUpdate>(
                  this, METHODID_STREAM_CLIMATE_UPDATES)))
          .addMethod(
            getAdjustTemperatureMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                smarthome.climate.Climate.TemperatureAdjustment,
                smarthome.climate.Climate.TemperatureAdjustmentAck>(
                  this, METHODID_ADJUST_TEMPERATURE)))
          .build();
    }
  }

  /**
   * <pre>
   * ClimateService defines all gRPC methods for managing and monitoring climate systems
   * </pre>
   */
  public static final class ClimateServiceStub extends io.grpc.stub.AbstractAsyncStub<ClimateServiceStub> {
    private ClimateServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClimateServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC: Client sends a temperature to set, server responds with a success status
     * </pre>
     */
    public void setTemperature(smarthome.climate.Climate.SetTemperatureRequest request,
        io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetTemperatureMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Unary RPC: Client requests current climate data (temperature/humidity)
     * </pre>
     */
    public void getTemperature(smarthome.climate.Climate.GetTemperatureRequest request,
        io.grpc.stub.StreamObserver<smarthome.climate.Climate.TemperatureResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTemperatureMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Server Streaming RPC: Server continuously streams climate updates at intervals
     * </pre>
     */
    public void streamClimateUpdates(smarthome.climate.Climate.ClimateStreamRequest request,
        io.grpc.stub.StreamObserver<smarthome.climate.Climate.ClimateUpdate> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamClimateUpdatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Bi-directional Streaming RPC: Client and server stream temperature adjustments and acknowledgements
     * </pre>
     */
    public io.grpc.stub.StreamObserver<smarthome.climate.Climate.TemperatureAdjustment> adjustTemperature(
        io.grpc.stub.StreamObserver<smarthome.climate.Climate.TemperatureAdjustmentAck> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getAdjustTemperatureMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * ClimateService defines all gRPC methods for managing and monitoring climate systems
   * </pre>
   */
  public static final class ClimateServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ClimateServiceBlockingStub> {
    private ClimateServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClimateServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC: Client sends a temperature to set, server responds with a success status
     * </pre>
     */
    public smarthome.common.Common.StatusResponse setTemperature(smarthome.climate.Climate.SetTemperatureRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetTemperatureMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Unary RPC: Client requests current climate data (temperature/humidity)
     * </pre>
     */
    public smarthome.climate.Climate.TemperatureResponse getTemperature(smarthome.climate.Climate.GetTemperatureRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTemperatureMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Server Streaming RPC: Server continuously streams climate updates at intervals
     * </pre>
     */
    public java.util.Iterator<smarthome.climate.Climate.ClimateUpdate> streamClimateUpdates(
        smarthome.climate.Climate.ClimateStreamRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamClimateUpdatesMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * ClimateService defines all gRPC methods for managing and monitoring climate systems
   * </pre>
   */
  public static final class ClimateServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ClimateServiceFutureStub> {
    private ClimateServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClimateServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC: Client sends a temperature to set, server responds with a success status
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<smarthome.common.Common.StatusResponse> setTemperature(
        smarthome.climate.Climate.SetTemperatureRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetTemperatureMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Unary RPC: Client requests current climate data (temperature/humidity)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<smarthome.climate.Climate.TemperatureResponse> getTemperature(
        smarthome.climate.Climate.GetTemperatureRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTemperatureMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_TEMPERATURE = 0;
  private static final int METHODID_GET_TEMPERATURE = 1;
  private static final int METHODID_STREAM_CLIMATE_UPDATES = 2;
  private static final int METHODID_ADJUST_TEMPERATURE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ClimateServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ClimateServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET_TEMPERATURE:
          serviceImpl.setTemperature((smarthome.climate.Climate.SetTemperatureRequest) request,
              (io.grpc.stub.StreamObserver<smarthome.common.Common.StatusResponse>) responseObserver);
          break;
        case METHODID_GET_TEMPERATURE:
          serviceImpl.getTemperature((smarthome.climate.Climate.GetTemperatureRequest) request,
              (io.grpc.stub.StreamObserver<smarthome.climate.Climate.TemperatureResponse>) responseObserver);
          break;
        case METHODID_STREAM_CLIMATE_UPDATES:
          serviceImpl.streamClimateUpdates((smarthome.climate.Climate.ClimateStreamRequest) request,
              (io.grpc.stub.StreamObserver<smarthome.climate.Climate.ClimateUpdate>) responseObserver);
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
        case METHODID_ADJUST_TEMPERATURE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.adjustTemperature(
              (io.grpc.stub.StreamObserver<smarthome.climate.Climate.TemperatureAdjustmentAck>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ClimateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClimateServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return smarthome.climate.Climate.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClimateService");
    }
  }

  private static final class ClimateServiceFileDescriptorSupplier
      extends ClimateServiceBaseDescriptorSupplier {
    ClimateServiceFileDescriptorSupplier() {}
  }

  private static final class ClimateServiceMethodDescriptorSupplier
      extends ClimateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ClimateServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ClimateServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClimateServiceFileDescriptorSupplier())
              .addMethod(getSetTemperatureMethod())
              .addMethod(getGetTemperatureMethod())
              .addMethod(getStreamClimateUpdatesMethod())
              .addMethod(getAdjustTemperatureMethod())
              .build();
        }
      }
    }
    return result;
  }
}
