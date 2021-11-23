package com.seezoon.grpc.client;

import com.seezoon.grpc.config.GrpcClientProperties;
import com.seezoon.grpc.config.GrpcClientProperties.StubProperties;
import io.grpc.ManagedChannelBuilder;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * discover grpc client by configuration
 */
@RequiredArgsConstructor
public class ConfigurationGrpcClientDiscovery implements GrpcClientDiscovery {

    private final GrpcClientProperties grpcClientProperties;


    @Override
    public List<GrpcClientDefinition> getGrpcClientDefinitions() {
        List<StubProperties> stubs = grpcClientProperties.getStubs();

        List<GrpcClientDefinition> definitions = new ArrayList<>();
        for (StubProperties stub : stubs) {
            definitions.add(new GrpcClientDefinition(stub.getClazz(), stub.getClazz().getName(),
                    GrpcStubFactory.create(stub.getClazz(), ManagedChannelBuilder.forTarget("127.0.0.1").build())));
        }
        return definitions;
    }
}
