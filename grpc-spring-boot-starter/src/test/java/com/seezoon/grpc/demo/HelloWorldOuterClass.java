// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hello_world.proto

package com.seezoon.grpc.demo;

public final class HelloWorldOuterClass {

    static final com.google.protobuf.Descriptors.Descriptor internal_static_com_seezoon_grpc_demo_HelloWorldRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_com_seezoon_grpc_demo_HelloWorldRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_com_seezoon_grpc_demo_HelloWorldResponse_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_com_seezoon_grpc_demo_HelloWorldResponse_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        String[] descriptorData = {"\n\021hello_world.proto\022\025com.seezoon.grpc.de"
                + "mo\"!\n\021HelloWorldRequest\022\014\n\004name\030\001 \001(\t\"%\n"
                + "\022HelloWorldResponse\022\017\n\007message\030\001 \001(\t2h\n\n"
                + "HelloWorld\022Z\n\003Say\022(.com.seezoon.grpc.dem" + "o.HelloWorldRequest\032).com.seezoon.grpc.d"
                + "emo.HelloWorldResponseB\031\n\025com.seezoon.gr" + "pc.demoP\001b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData, new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_com_seezoon_grpc_demo_HelloWorldRequest_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_com_seezoon_grpc_demo_HelloWorldRequest_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_seezoon_grpc_demo_HelloWorldRequest_descriptor, new String[]{"Name",});
        internal_static_com_seezoon_grpc_demo_HelloWorldResponse_descriptor = getDescriptor().getMessageTypes().get(1);
        internal_static_com_seezoon_grpc_demo_HelloWorldResponse_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_seezoon_grpc_demo_HelloWorldResponse_descriptor, new String[]{"Message",});
    }
    private HelloWorldOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    // @@protoc_insertion_point(outer_class_scope)
}
