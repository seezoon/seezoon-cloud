package com.seezoon.grpc.client;

import com.seezoon.grpc.config.GrpcClientProperties;
import com.seezoon.grpc.config.GrpcClientProperties.ChannelProperties;
import io.grpc.Channel;
import io.grpc.ClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

@Slf4j
@RequiredArgsConstructor
public class GrpcChannelFactory {

    private final static Map<String, ChannelDefinition> channels = new HashMap<>();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                log.info("*** shutting down gRPC server since JVM is shutting down");
                for (Entry<String, ChannelDefinition> entry : channels.entrySet()) {
                    ChannelDefinition channelDefinition = entry.getValue();
                    ManagedChannel channel = channelDefinition.getChannel();
                    if (!channel.isTerminated()) {
                        Duration duration = Optional.ofNullable(channelDefinition.getShutdownAwait())
                                .orElse(Duration.ofMillis(Long.MAX_VALUE));
                        try {
                            channel.shutdown().awaitTermination(duration.toMillis(), TimeUnit.MILLISECONDS);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                log.info("client shut down");
            }
        });
    }

    private final ApplicationContext applicationContext;
    private final GrpcClientProperties grpcClientProperties;

    public Channel get(String channelName) {
        Objects.requireNonNull(channelName);
        return channels.computeIfAbsent(channelName, k -> this.create(channelName)).getChannel();
    }

    private ChannelDefinition create(String channelName) {
        ChannelProperties channelProperties = grpcClientProperties.getChannels().get(channelName);
        Assert.notNull(channelProperties, "can not find channel:" + channelName);
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder
                .forTarget(Objects.requireNonNull(channelProperties.getTarget()));
        configure(channelBuilder);
        configureInterceptors(channelProperties, channelBuilder);
        ManagedChannel channel = channelBuilder.build();
        return new ChannelDefinition(channelName, channel, grpcClientProperties.getCommon().getShutdownAwait());
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


    @AllArgsConstructor
    @Getter
    private static class ChannelDefinition {

        private String name;
        private ManagedChannel channel;
        private Duration shutdownAwait;
    }
}
