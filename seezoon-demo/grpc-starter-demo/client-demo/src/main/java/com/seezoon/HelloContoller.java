package com.seezoon;

import com.google.common.util.concurrent.ListenableFuture;
import com.seezoon.grpc.annotation.GrpcClient;
import com.seezoon.helloworld.HelloWorldGrpc.HelloWorldBlockingStub;
import com.seezoon.helloworld.HelloWorldGrpc.HelloWorldFutureStub;
import com.seezoon.helloworld.HelloWorldGrpc.HelloWorldStub;
import com.seezoon.helloworld.HelloWorldRequest;
import com.seezoon.helloworld.HelloWorldResponse;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloContoller {

    @GrpcClient
    private HelloWorldStub helloWorldStub;
    @GrpcClient
    private HelloWorldBlockingStub helloWorldBlockingStub;
    @GrpcClient
    private HelloWorldFutureStub helloWorldFutureStub;

    @RequestMapping("/")
    public void stub(String name) {
        HelloWorldRequest request = HelloWorldRequest.newBuilder().setName(name).build();
        helloWorldStub.say(request, new StreamObserver<HelloWorldResponse>() {
            @Override
            public void onNext(HelloWorldResponse value) {
                System.out.println("response: " + value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                log.error("error", t);
            }

            @Override
            public void onCompleted() {
                log.debug("onCompleted");
            }
        });
    }

    @RequestMapping("/blockingStub")
    public String blockingStub(String name) {
        HelloWorldRequest request = HelloWorldRequest.newBuilder().setName(name).build();
        HelloWorldResponse helloWorldResponse = helloWorldBlockingStub.say(request);
        return helloWorldResponse.getMessage();
    }

    @RequestMapping("/futureStub")
    public String futureStub(String name) throws ExecutionException, InterruptedException {
        HelloWorldRequest request = HelloWorldRequest.newBuilder().setName(name).build();
        ListenableFuture<HelloWorldResponse> future = helloWorldFutureStub.say(request);
        HelloWorldResponse helloWorldResponse = future.get();
        return helloWorldResponse.getMessage();

    }
}
