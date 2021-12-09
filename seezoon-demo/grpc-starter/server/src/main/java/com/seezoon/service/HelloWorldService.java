package com.seezoon.service;

import com.seezoon.grpc.annotation.GrpcService;
import com.seezoon.helloworld.HelloWorldGrpc.HelloWorldImplBase;
import com.seezoon.helloworld.HelloWorldRequest;
import com.seezoon.helloworld.HelloWorldResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@GrpcService
@Slf4j
public class HelloWorldService extends HelloWorldImplBase {

    @Override
    public void say(HelloWorldRequest request, StreamObserver<HelloWorldResponse> responseObserver) {
        log.info("recieve request name :{}", request.getName());
        responseObserver.onNext(HelloWorldResponse.newBuilder().setMessage("hello " + request.getName()).build());
        responseObserver.onCompleted();
    }
}
