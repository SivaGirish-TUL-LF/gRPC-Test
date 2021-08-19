package com.tul.shopping.service;

import com.tul.shopping.model.User;
import com.tul.shopping.repository.UserRepository;
import com.tul.shopping.stubs.user.Gender;
import com.tul.shopping.stubs.user.UserRequest;
import com.tul.shopping.stubs.user.UserResponse;
import com.tul.shopping.stubs.user.userServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UserService extends userServiceGrpc.userServiceImplBase{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void getUserDetails(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        User user = userRepository.findByUsername(request.getUsername());
        UserResponse.Builder userResponseBuilder =
                UserResponse.newBuilder()
                        .setId(user.getId())
                        .setUsername(user.getUsername())
                        .setName(user.getName())
                        .setAge(user.getAge())
                        .setGender(Gender.valueOf(user.getGender()));
        UserResponse userResponse = userResponseBuilder.build();

        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }
}
