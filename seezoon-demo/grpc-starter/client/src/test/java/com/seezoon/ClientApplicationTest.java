package com.seezoon;

import com.seezoon.grpc.annotation.GrpcClient;
import com.seezoon.helloworld.HelloWorldGrpc.HelloWorldStub;
import com.seezoon.helloworld.HelloWorldRequest;
import com.seezoon.helloworld.HelloWorldResponse;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientApplicationTest {

    @GrpcClient
    private HelloWorldStub helloWorldStub;

    @Test
    public void testSay() throws InterruptedException {
        helloWorldStub
                .say(HelloWorldRequest.newBuilder().setName("df").build(), new StreamObserver<HelloWorldResponse>() {
                    @Override
                    public void onNext(HelloWorldResponse value) {
                        System.out.println("response: " + value.getMessage());
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("error:" + t.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }
                });
        Thread.sleep(2000);
    }
}