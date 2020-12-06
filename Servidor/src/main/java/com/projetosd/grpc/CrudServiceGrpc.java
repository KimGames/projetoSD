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
  private static volatile io.grpc.MethodDescriptor<com.projetosd.grpc.Dto,
      com.projetosd.grpc.ProcessingResponse> getCreateDtoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDto",
      requestType = com.projetosd.grpc.Dto.class,
      responseType = com.projetosd.grpc.ProcessingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.projetosd.grpc.Dto,
      com.projetosd.grpc.ProcessingResponse> getCreateDtoMethod() {
    io.grpc.MethodDescriptor<com.projetosd.grpc.Dto, com.projetosd.grpc.ProcessingResponse> getCreateDtoMethod;
    if ((getCreateDtoMethod = CrudServiceGrpc.getCreateDtoMethod) == null) {
      synchronized (CrudServiceGrpc.class) {
        if ((getCreateDtoMethod = CrudServiceGrpc.getCreateDtoMethod) == null) {
          CrudServiceGrpc.getCreateDtoMethod = getCreateDtoMethod = 
              io.grpc.MethodDescriptor.<com.projetosd.grpc.Dto, com.projetosd.grpc.ProcessingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "crud.CrudService", "CreateDto"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.Dto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.ProcessingResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new CrudServiceMethodDescriptorSupplier("CreateDto"))
                  .build();
          }
        }
     }
     return getCreateDtoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.projetosd.grpc.Dto,
      com.projetosd.grpc.ProcessingResponse> getUpdateDtoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateDto",
      requestType = com.projetosd.grpc.Dto.class,
      responseType = com.projetosd.grpc.ProcessingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.projetosd.grpc.Dto,
      com.projetosd.grpc.ProcessingResponse> getUpdateDtoMethod() {
    io.grpc.MethodDescriptor<com.projetosd.grpc.Dto, com.projetosd.grpc.ProcessingResponse> getUpdateDtoMethod;
    if ((getUpdateDtoMethod = CrudServiceGrpc.getUpdateDtoMethod) == null) {
      synchronized (CrudServiceGrpc.class) {
        if ((getUpdateDtoMethod = CrudServiceGrpc.getUpdateDtoMethod) == null) {
          CrudServiceGrpc.getUpdateDtoMethod = getUpdateDtoMethod = 
              io.grpc.MethodDescriptor.<com.projetosd.grpc.Dto, com.projetosd.grpc.ProcessingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "crud.CrudService", "UpdateDto"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.Dto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.ProcessingResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new CrudServiceMethodDescriptorSupplier("UpdateDto"))
                  .build();
          }
        }
     }
     return getUpdateDtoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.projetosd.grpc.Dto,
      com.projetosd.grpc.ProcessingResponse> getDeleteDtoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteDto",
      requestType = com.projetosd.grpc.Dto.class,
      responseType = com.projetosd.grpc.ProcessingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.projetosd.grpc.Dto,
      com.projetosd.grpc.ProcessingResponse> getDeleteDtoMethod() {
    io.grpc.MethodDescriptor<com.projetosd.grpc.Dto, com.projetosd.grpc.ProcessingResponse> getDeleteDtoMethod;
    if ((getDeleteDtoMethod = CrudServiceGrpc.getDeleteDtoMethod) == null) {
      synchronized (CrudServiceGrpc.class) {
        if ((getDeleteDtoMethod = CrudServiceGrpc.getDeleteDtoMethod) == null) {
          CrudServiceGrpc.getDeleteDtoMethod = getDeleteDtoMethod = 
              io.grpc.MethodDescriptor.<com.projetosd.grpc.Dto, com.projetosd.grpc.ProcessingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "crud.CrudService", "DeleteDto"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.Dto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.ProcessingResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new CrudServiceMethodDescriptorSupplier("DeleteDto"))
                  .build();
          }
        }
     }
     return getDeleteDtoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.projetosd.grpc.Empty,
      com.projetosd.grpc.DtoList> getGetDtoListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDtoList",
      requestType = com.projetosd.grpc.Empty.class,
      responseType = com.projetosd.grpc.DtoList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.projetosd.grpc.Empty,
      com.projetosd.grpc.DtoList> getGetDtoListMethod() {
    io.grpc.MethodDescriptor<com.projetosd.grpc.Empty, com.projetosd.grpc.DtoList> getGetDtoListMethod;
    if ((getGetDtoListMethod = CrudServiceGrpc.getGetDtoListMethod) == null) {
      synchronized (CrudServiceGrpc.class) {
        if ((getGetDtoListMethod = CrudServiceGrpc.getGetDtoListMethod) == null) {
          CrudServiceGrpc.getGetDtoListMethod = getGetDtoListMethod = 
              io.grpc.MethodDescriptor.<com.projetosd.grpc.Empty, com.projetosd.grpc.DtoList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "crud.CrudService", "GetDtoList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.projetosd.grpc.DtoList.getDefaultInstance()))
                  .setSchemaDescriptor(new CrudServiceMethodDescriptorSupplier("GetDtoList"))
                  .build();
          }
        }
     }
     return getGetDtoListMethod;
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
    public void createDto(com.projetosd.grpc.Dto request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.ProcessingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateDtoMethod(), responseObserver);
    }

    /**
     */
    public void updateDto(com.projetosd.grpc.Dto request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.ProcessingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateDtoMethod(), responseObserver);
    }

    /**
     */
    public void deleteDto(com.projetosd.grpc.Dto request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.ProcessingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteDtoMethod(), responseObserver);
    }

    /**
     */
    public void getDtoList(com.projetosd.grpc.Empty request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.DtoList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDtoListMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateDtoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.projetosd.grpc.Dto,
                com.projetosd.grpc.ProcessingResponse>(
                  this, METHODID_CREATE_DTO)))
          .addMethod(
            getUpdateDtoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.projetosd.grpc.Dto,
                com.projetosd.grpc.ProcessingResponse>(
                  this, METHODID_UPDATE_DTO)))
          .addMethod(
            getDeleteDtoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.projetosd.grpc.Dto,
                com.projetosd.grpc.ProcessingResponse>(
                  this, METHODID_DELETE_DTO)))
          .addMethod(
            getGetDtoListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.projetosd.grpc.Empty,
                com.projetosd.grpc.DtoList>(
                  this, METHODID_GET_DTO_LIST)))
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
    public void createDto(com.projetosd.grpc.Dto request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.ProcessingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateDtoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateDto(com.projetosd.grpc.Dto request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.ProcessingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateDtoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteDto(com.projetosd.grpc.Dto request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.ProcessingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteDtoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDtoList(com.projetosd.grpc.Empty request,
        io.grpc.stub.StreamObserver<com.projetosd.grpc.DtoList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDtoListMethod(), getCallOptions()), request, responseObserver);
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
    public com.projetosd.grpc.ProcessingResponse createDto(com.projetosd.grpc.Dto request) {
      return blockingUnaryCall(
          getChannel(), getCreateDtoMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.projetosd.grpc.ProcessingResponse updateDto(com.projetosd.grpc.Dto request) {
      return blockingUnaryCall(
          getChannel(), getUpdateDtoMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.projetosd.grpc.ProcessingResponse deleteDto(com.projetosd.grpc.Dto request) {
      return blockingUnaryCall(
          getChannel(), getDeleteDtoMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.projetosd.grpc.DtoList getDtoList(com.projetosd.grpc.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetDtoListMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.projetosd.grpc.ProcessingResponse> createDto(
        com.projetosd.grpc.Dto request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateDtoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.projetosd.grpc.ProcessingResponse> updateDto(
        com.projetosd.grpc.Dto request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateDtoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.projetosd.grpc.ProcessingResponse> deleteDto(
        com.projetosd.grpc.Dto request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteDtoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.projetosd.grpc.DtoList> getDtoList(
        com.projetosd.grpc.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDtoListMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_DTO = 0;
  private static final int METHODID_UPDATE_DTO = 1;
  private static final int METHODID_DELETE_DTO = 2;
  private static final int METHODID_GET_DTO_LIST = 3;

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
        case METHODID_CREATE_DTO:
          serviceImpl.createDto((com.projetosd.grpc.Dto) request,
              (io.grpc.stub.StreamObserver<com.projetosd.grpc.ProcessingResponse>) responseObserver);
          break;
        case METHODID_UPDATE_DTO:
          serviceImpl.updateDto((com.projetosd.grpc.Dto) request,
              (io.grpc.stub.StreamObserver<com.projetosd.grpc.ProcessingResponse>) responseObserver);
          break;
        case METHODID_DELETE_DTO:
          serviceImpl.deleteDto((com.projetosd.grpc.Dto) request,
              (io.grpc.stub.StreamObserver<com.projetosd.grpc.ProcessingResponse>) responseObserver);
          break;
        case METHODID_GET_DTO_LIST:
          serviceImpl.getDtoList((com.projetosd.grpc.Empty) request,
              (io.grpc.stub.StreamObserver<com.projetosd.grpc.DtoList>) responseObserver);
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
      return com.projetosd.grpc.CrudProto.getDescriptor();
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
              .addMethod(getCreateDtoMethod())
              .addMethod(getUpdateDtoMethod())
              .addMethod(getDeleteDtoMethod())
              .addMethod(getGetDtoListMethod())
              .build();
        }
      }
    }
    return result;
  }
}
