package com.seezoon.grpc.server;

import com.seezoon.grpc.annotation.GrpcService;
import io.grpc.BindableService;
import io.grpc.ServerInterceptor;
import io.grpc.ServerInterceptors;
import io.grpc.ServerServiceDefinition;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * thats annotated by  {@link com.seezoon.grpc.annotation.GrpcService}
 */
public class AnnotationGrpcServiceDiscovery implements ApplicationContextAware, GrpcServiceDiscovery {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public List<GrpcServiceDefinition> findGrpcServices() {
        List<GrpcServiceDefinition> grpcServiceDefinitions = new ArrayList<>();
        String[] beanNames = this.applicationContext.getBeanNamesForAnnotation(GrpcService.class);
        for (String beanName : beanNames) {
            // a grpc service bean must implements BindableService
            BindableService bindableService = this.applicationContext.getBean(beanName, BindableService.class);
            ServerServiceDefinition serverServiceDefinition = bindableService.bindService();
            // set interceptors
            this.bindInterceptors(beanName, serverServiceDefinition);
            grpcServiceDefinitions
                    .add(new GrpcServiceDefinition(beanName, bindableService.getClass(), serverServiceDefinition));
        }
        return grpcServiceDefinitions;
    }

    private void bindInterceptors(String beanName, ServerServiceDefinition serverServiceDefinition) {
        GrpcService GrpcServiceAnnotation = this.applicationContext.findAnnotationOnBean(beanName, GrpcService.class);
        Class<? extends ServerInterceptor>[] interceptorClasses = GrpcServiceAnnotation.interceptors();
        if (interceptorClasses.length > 0) {
            List<ServerInterceptor> serverInterceptorBeans = new ArrayList<>();
            for (Class<? extends ServerInterceptor> interceptorClass : interceptorClasses) {
                // if will throw BeansException when interceptor class not find
                ServerInterceptor serverInterceptor = this.applicationContext.getBean(interceptorClass);
            }
            ServerInterceptors.intercept(serverServiceDefinition, serverInterceptorBeans);
        }
    }
}
