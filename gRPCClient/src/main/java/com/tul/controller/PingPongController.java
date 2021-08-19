package com.tul.controller;


import com.tul.server.GRPCClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPongController {

    GRPCClientService grpcClientService;

    @Autowired
    public PingPongController(GRPCClientService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @GetMapping("/ping")
    public String ping() {
        return grpcClientService.ping();
    }
}
