package com.seezoon.grpc.server;

import java.util.List;

/**
 * Discover grpc service
 */
public interface GrpcServiceDiscovery {


    /**
     * find all grpc service definition
     *
     * @return
     */
    List<GrpcServiceDefinition> findGrpcServices();
}
