package com.seezoon.grpc.config;

import io.grpc.stub.AbstractStub;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "grpc.client")
public class GrpcClientProperties {

    private String a;
    private List<ChannelProperties> channels = Collections.EMPTY_LIST;

    private List<StubProperties> stubs = Collections.EMPTY_LIST;

    @Getter
    @Setter
    public static class ChannelProperties {

        private String target;
    }

    @Getter
    @Setter
    public static class StubProperties {

        private String name;
        private Class<? extends AbstractStub> clazz;
    }

}
