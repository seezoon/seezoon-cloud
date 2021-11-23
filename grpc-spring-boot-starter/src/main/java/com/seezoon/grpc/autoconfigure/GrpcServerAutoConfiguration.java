package com.seezoon.grpc.autoconfigure;

import com.seezoon.grpc.config.GrpcServerProperties;
import com.seezoon.grpc.server.AnnotationGrpcServiceDiscovery;
import com.seezoon.grpc.server.GrpcServiceDiscovery;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({GrpcServerProperties.class})
@RequiredArgsConstructor
public class GrpcServerAutoConfiguration {

    private final GrpcServerProperties grpcServerProperties;

    @Bean
    public GrpcServiceDiscovery annotationGrpcServiceDiscovery() {
        return new AnnotationGrpcServiceDiscovery();
    }

}
