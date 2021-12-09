package com.seezoon.grpc.client;

import com.seezoon.grpc.config.GrpcClientProperties.ChannelProperties;
import io.grpc.Channel;
import io.grpc.ClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@Slf4j
@RequiredArgsConstructor
public class GrpcChannelFactory {

    private final static Map<String, Channel> channels = new HashMap<>();
    private final ApplicationContext applicationContext;

    public Channel create(String name, ChannelProperties channelProperties) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(channelProperties);
        return channels.computeIfAbsent(name, k -> this.create(channelProperties));
    }

    private Channel create(ChannelProperties channelProperties) {
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder
                .forTarget(Objects.requireNonNull(channelProperties.getTarget()));
        configure(channelBuilder);
        configureInterceptors(channelProperties, channelBuilder);
        ManagedChannel channel = channelBuilder.build();
        return channel;
    }

    private void configureInterceptors(ChannelProperties channelProperties, ManagedChannelBuilder<?> channelBuilder) {
        List<Class<? extends ClientInterceptor>> interceptors = channelProperties.getInterceptors();
        List<ClientInterceptor> clientInterceptors = new ArrayList<>();
        for (Class<? extends ClientInterceptor> interceptor : interceptors) {
            ClientInterceptor clientInterceptor = applicationContext.getBean(interceptor);
            if (null == clientInterceptor) {
                throw new IllegalArgumentException("client interceptor bean [" + interceptor.getName() + "] not found");
            }
            clientInterceptors.add(clientInterceptor);
        }
        channelBuilder.intercept(clientInterceptors);
    }

    private void configure(ManagedChannelBuilder<?> channelBuilder) {
        channelBuilder.usePlaintext();
    }
}
