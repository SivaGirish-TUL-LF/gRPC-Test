package com.tul.service;

import com.tul.gRPCClient.stubs.client.PingPongServiceGrpc;
import com.tul.gRPCClient.stubs.client.PingRequest;
import com.tul.gRPCClient.stubs.client.PongResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class PingPongService extends PingPongServiceGrpc.PingPongServiceImplBase {
    @Override
    public void ping(PingRequest request, StreamObserver<PongResponse> responseObserver) {
        String ping = new StringBuilder()
                .append("pong")
                .toString();

        PongResponse response = PongResponse.newBuilder()
                .setPong(ping)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}