package com.seezoon.grpc.client;

import com.seezoon.grpc.config.GrpcClientProperties;
import com.seezoon.grpc.config.GrpcClientProperties.ChannelProperties;
import com.seezoon.grpc.config.GrpcClientProperties.StubProperties;
import io.grpc.Channel;
import io.grpc.stub.AbstractStub;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

/**
 * discover grpc client by configuration
 */
@RequiredArgsConstructor
public class GrpcClientDiscovery {

    private final GrpcClientProperties grpcClientProperties;
    private final ApplicationContext applicationContext;


    public List<GrpcClientDefinition> getGrpcClientDefinitions() {
        List<StubProperties> stubs = grpcClientProperties.getStubs();
        Map<String, ChannelProperties> channels = grpcClientProperties.getChannels();
        List<GrpcClientDefinition> definitions = new ArrayList<>();
        GrpcChannelFactory grpcChannelFactory = new GrpcChannelFactory(applicationContext);
        for (StubProperties stub : stubs) {
            String channelName = Objects.requireNonNull(stub.getChannel(), "stub channel must not empty");
            ChannelProperties channelProperties = channels.get(channelName);
            Assert.notNull(channelProperties, "can not find channel:" + stub.getChannel());
            // create channel
            Channel channel = grpcChannelFactory.create(channelName, channelProperties);
            AbstractStub abstractStub = GrpcStubFactory.create(stub.getClazz(), channel);
            Duration timeout = channelProperties.getTimeout();
            if (null != timeout) {
                // 这个属性没法在channel上设置
                abstractStub.withDeadlineAfter(timeout.toMillis(), TimeUnit.MILLISECONDS);
            }
            definitions.add(new GrpcClientDefinition(stub.getClazz(), stub.getClazz().getName(), abstractStub));
        }
        return definitions;
    }
}
