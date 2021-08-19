package com.tul.server;

import com.tul.gRPCClient.stubs.client.PingPongServiceGrpc;
import com.tul.gRPCClient.stubs.client.PingRequest;
import com.tul.gRPCClient.stubs.client.PongResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class GRPCClientService {
    public String ping() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        PingPongServiceGrpc.PingPongServiceBlockingStub stub
                = PingPongServiceGrpc.newBlockingStub(channel);

        PongResponse helloResponse = stub.ping(PingRequest.newBuilder()
                .setPing("")
                .build());

        channel.shutdown();

        return helloResponse.getPong();
    }
}

