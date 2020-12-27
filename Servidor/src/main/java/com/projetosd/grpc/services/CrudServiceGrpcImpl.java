package com.projetosd.grpc.services;

import com.projetosd.grpc.resources.EventSource;
import com.projetosd.grpc.resources.Input;
import com.projetosd.grpc.*;
import io.grpc.stub.StreamObserver;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class CrudServiceGrpcImpl extends CrudServiceGrpc.CrudServiceImplBase {

    public BlockingQueue<Input> requisitionsQueue;

    public CrudServiceGrpcImpl(BlockingQueue<Input> _requisitions) {
        this.requisitionsQueue = _requisitions;
    }

    @Override
    public void set(SetRequest request, StreamObserver<ResponseModel> responseObserver) {
        EventSource eventSource = new EventSource();

        eventSource.addObserver(new EventSource.Observer() {
            @Override
            public void update(final String event) {
                ResponseModel response = ResponseModel.newBuilder().setResponse(event).build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        });

        Input input = new Input(BigInteger.valueOf(request.getId()), request.getContent(), 0, eventSource);
        requisitionsQueue.add(input);
    }

    @Override
    public void get(GetRequest request, StreamObserver<ResponseModel> responseObserver) {
        EventSource eventSource = new EventSource();

        eventSource.addObserver(new EventSource.Observer() {
            @Override
            public void update(final String event) {
                ResponseModel response = ResponseModel.newBuilder().setResponse(event).build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        });

        Input input;

        if (request.getAll()) {
            input = new Input(BigInteger.valueOf(-1), "*", 1, eventSource);
        } else {
            input = new Input(BigInteger.valueOf(request.getId()), "", 1, eventSource);
        }

        requisitionsQueue.add(input);
    }

    @Override
    public void testAndSet(TestAndSetRequest request, StreamObserver<ResponseModel> responseObserver) {
        EventSource eventSource = new EventSource();

        eventSource.addObserver(new EventSource.Observer() {
            @Override
            public void update(final String event) {
                ResponseModel response = ResponseModel.newBuilder().setResponse(event).build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        });

        Input input = new Input(BigInteger.valueOf(request.getId()), request.getContent(), 2, eventSource);

        requisitionsQueue.add(input);
    }

    @Override
    public void del(DelRequest request, StreamObserver<ResponseModel> responseObserver) {
        EventSource eventSource = new EventSource();

        eventSource.addObserver(new EventSource.Observer() {
            @Override
            public void update(final String event) {
                ResponseModel response = ResponseModel.newBuilder().setResponse(event).build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        });

        Input input = new Input(BigInteger.valueOf(request.getId()), "", 3, eventSource);

        requisitionsQueue.add(input);
    }
}