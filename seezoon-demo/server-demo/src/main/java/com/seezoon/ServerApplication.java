package com.seezoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//go install github.com/fullstorydev/grpcui/cmd/grpcui@latest
//go install github.com/fullstorydev/grpcurl/cmd/grpcurl@latest

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
