package com.seezoon.grpc.client;

import java.util.List;

public interface GrpcClientDiscovery {

    List<GrpcClientDefinition> getGrpcClientDefinitions();
}
