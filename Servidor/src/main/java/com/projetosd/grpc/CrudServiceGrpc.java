package com.projetosd.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: crudService.proto")
public final class CrudServiceGrpc {

  private CrudServiceGrpc() {}

  public static final String SERVICE_NAME = "crud.CrudService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.projetosd.grpc.SetRequest,
      com.projetosd.grpc.ResponseModel> getSetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "set",
      requestType = com.projetosd.grpc.SetRequest.class,
      responseType = com.projetosd.grpc.ResponseModel.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.projetosd.grpc.SetRequest,
      com.projetosd.grpc.ResponseModel> getSetMethod() {
    io.grpc.MethodDescriptor<com.projetosd.grpc.SetRequest, com.projetosd.grpc.ResponseModel> getSetMethod;
    if ((getSetMethod = CrudServiceGrpc.getSetMethod) == null) {
      synchronized (CrudServiceGrpc.class) {
        if ((getSetMethod = CrudServiceGrpc.getSetMethod) == null) {
          CrudServiceGrpc.getSetMethod = getSetMethod = 
              io.grpc.MethodDescriptor.<com.projetosd.grpc.SetRequest, com.projetosd.grpc.ResponseModel>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "crud.CrudService", "set"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.SetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.ResponseModel.getDefaultInstance()))
                  .setSchemaDescriptor(new CrudServiceMethodDescriptorSupplier("set"))
                  .build();
          }
        }
     }
     return getSetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.projetosd.grpc.GetRequest,
      com.projetosd.grpc.ResponseModel> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "get",
      requestType = com.projetosd.grpc.GetRequest.class,
      responseType = com.projetosd.grpc.ResponseModel.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.projetosd.grpc.GetRequest,
      com.projetosd.grpc.ResponseModel> getGetMethod() {
    io.grpc.MethodDescriptor<com.projetosd.grpc.GetRequest, com.projetosd.grpc.ResponseModel> getGetMethod;
    if ((getGetMethod = CrudServiceGrpc.getGetMethod) == null) {
      synchronized (CrudServiceGrpc.class) {
        if ((getGetMethod = CrudServiceGrpc.getGetMethod) == null) {
          CrudServiceGrpc.getGetMethod = getGetMethod = 
              io.grpc.MethodDescriptor.<com.projetosd.grpc.GetRequest, com.projetosd.grpc.ResponseModel>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "crud.CrudService", "get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.GetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.ResponseModel.getDefaultInstance()))
                  .setSchemaDescriptor(new CrudServiceMethodDescriptorSupplier("get"))
                  .build();
          }
        }
     }
     return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.projetosd.grpc.TestAndSetRequest,
      com.projetosd.grpc.ResponseModel> getTestAndSetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "testAndSet",
      requestType = com.projetosd.grpc.TestAndSetRequest.class,
      responseType = com.projetosd.grpc.ResponseModel.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.projetosd.grpc.TestAndSetRequest,
      com.projetosd.grpc.ResponseModel> getTestAndSetMethod() {
    io.grpc.MethodDescriptor<com.projetosd.grpc.TestAndSetRequest, com.projetosd.grpc.ResponseModel> getTestAndSetMethod;
    if ((getTestAndSetMethod = CrudServiceGrpc.getTestAndSetMethod) == null) {
      synchronized (CrudServiceGrpc.class) {
        if ((getTestAndSetMethod = CrudServiceGrpc.getTestAndSetMethod) == null) {
          CrudServiceGrpc.getTestAndSetMethod = getTestAndSetMethod = 
              io.grpc.MethodDescriptor.<com.projetosd.grpc.TestAndSetRequest, com.projetosd.grpc.ResponseModel>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "crud.CrudService", "testAndSet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.TestAndSetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.ResponseModel.getDefaultInstance()))
                  .setSchemaDescriptor(new CrudServiceMethodDescriptorSupplier("testAndSet"))
                  .build();
          }
        }
     }
     return getTestAndSetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.projetosd.grpc.DelRequest,
      com.projetosd.grpc.ResponseModel> getDelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "del",
      requestType = com.projetosd.grpc.DelRequest.class,
      responseType = com.projetosd.grpc.ResponseModel.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.projetosd.grpc.DelRequest,
      com.projetosd.grpc.ResponseModel> getDelMethod() {
    io.grpc.MethodDescriptor<com.projetosd.grpc.DelRequest, com.projetosd.grpc.ResponseModel> getDelMethod;
    if ((getDelMethod = CrudServiceGrpc.getDelMethod) == null) {
      synchronized (CrudServiceGrpc.class) {
        if ((getDelMethod = CrudServiceGrpc.getDelMethod) == null) {
          CrudServiceGrpc.getDelMethod = getDelMethod = 
              io.grpc.MethodDescriptor.<com.projetosd.grpc.DelRequest, com.projetosd.grpc.ResponseModel>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "crud.CrudService", "del"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.DelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.ResponseModel.getDefaultInstance()))
                  .setSchemaDescriptor(new CrudServiceMethodDescriptorSupplier("del"))
                  .build();
          }
        }
     }
     return getDelMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CrudServiceStub newStub(io.grpc.Channel channel) {
    return new CrudServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CrudServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CrudServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CrudServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CrudServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CrudServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void set(com.projetosd.grpc.SetRequest request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.ResponseModel> responseObserver) {
      asyncUnimplementedUnaryCall(getSetMethod(), responseObserver);
    }

    /**
     */
    public void get(com.projetosd.grpc.GetRequest request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.ResponseModel> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void testAndSet(com.projetosd.grpc.TestAndSetRequest request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.ResponseModel> responseObserver) {
      asyncUnimplementedUnaryCall(getTestAndSetMethod(), responseObserver);
    }

    /**
     */
    public void del(com.projetosd.grpc.DelRequest request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.ResponseModel> responseObserver) {
      asyncUnimplementedUnaryCall(getDelMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.projetosd.grpc.SetRequest,
                com.projetosd.grpc.ResponseModel>(
                  this, METHODID_SET)))
          .addMethod(
            getGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.projetosd.grpc.GetRequest,
                com.projetosd.grpc.ResponseModel>(
                  this, METHODID_GET)))
          .addMethod(
            getTestAndSetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.projetosd.grpc.TestAndSetRequest,
                com.projetosd.grpc.ResponseModel>(
                  this, METHODID_TEST_AND_SET)))
          .addMethod(
            getDelMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.projetosd.grpc.DelRequest,
                com.projetosd.grpc.ResponseModel>(
                  this, METHODID_DEL)))
          .build();
    }
  }

  /**
   */
  public static final class CrudServiceStub extends io.grpc.stub.AbstractStub<CrudServiceStub> {
    private CrudServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CrudServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CrudServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CrudServiceStub(channel, callOptions);
    }

    /**
     */
    public void set(com.projetosd.grpc.SetRequest request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.ResponseModel> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void get(com.projetosd.grpc.GetRequest request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.ResponseModel> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void testAndSet(com.projetosd.grpc.TestAndSetRequest request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.ResponseModel> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTestAndSetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void del(com.projetosd.grpc.DelRequest request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.ResponseModel> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CrudServiceBlockingStub extends io.grpc.stub.AbstractStub<CrudServiceBlockingStub> {
    private CrudServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CrudServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CrudServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CrudServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.projetosd.grpc.ResponseModel set(com.projetosd.grpc.SetRequest request) {
      return blockingUnaryCall(
          getChannel(), getSetMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.projetosd.grpc.ResponseModel get(com.projetosd.grpc.GetRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.projetosd.grpc.ResponseModel testAndSet(com.projetosd.grpc.TestAndSetRequest request) {
      return blockingUnaryCall(
          getChannel(), getTestAndSetMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.projetosd.grpc.ResponseModel del(com.projetosd.grpc.DelRequest request) {
      return blockingUnaryCall(
          getChannel(), getDelMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CrudServiceFutureStub extends io.grpc.stub.AbstractStub<CrudServiceFutureStub> {
    private CrudServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CrudServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CrudServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CrudServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.projetosd.grpc.ResponseModel> set(
        com.projetosd.grpc.SetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.projetosd.grpc.ResponseModel> get(
        com.projetosd.grpc.GetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.projetosd.grpc.ResponseModel> testAndSet(
        com.projetosd.grpc.TestAndSetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTestAndSetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.projetosd.grpc.ResponseModel> del(
        com.projetosd.grpc.DelRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDelMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET = 0;
  private static final int METHODID_GET = 1;
  private static final int METHODID_TEST_AND_SET = 2;
  private static final int METHODID_DEL = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CrudServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CrudServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET:
          serviceImpl.set((com.projetosd.grpc.SetRequest) request,
              (io.grpc.stub.StreamObserver<com.projetosd.grpc.ResponseModel>) responseObserver);
          break;
        case METHODID_GET:
          serviceImpl.get((com.projetosd.grpc.GetRequest) request,
              (io.grpc.stub.StreamObserver<com.projetosd.grpc.ResponseModel>) responseObserver);
          break;
        case METHODID_TEST_AND_SET:
          serviceImpl.testAndSet((com.projetosd.grpc.TestAndSetRequest) request,
              (io.grpc.stub.StreamObserver<com.projetosd.grpc.ResponseModel>) responseObserver);
          break;
        case METHODID_DEL:
          serviceImpl.del((com.projetosd.grpc.DelRequest) request,
              (io.grpc.stub.StreamObserver<com.projetosd.grpc.ResponseModel>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CrudServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CrudServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.projetosd.grpc.CrudServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CrudService");
    }
  }

  private static final class CrudServiceFileDescriptorSupplier
      extends CrudServiceBaseDescriptorSupplier {
    CrudServiceFileDescriptorSupplier() {}
  }

  private static final class CrudServiceMethodDescriptorSupplier
      extends CrudServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CrudServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CrudServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CrudServiceFileDescriptorSupplier())
              .addMethod(getSetMethod())
              .addMethod(getGetMethod())
              .addMethod(getTestAndSetMethod())
              .addMethod(getDelMethod())
              .build();
        }
      }
    }
    return result;
  }
}
