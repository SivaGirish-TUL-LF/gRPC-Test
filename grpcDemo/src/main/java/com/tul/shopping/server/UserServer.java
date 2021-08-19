package com.tul.shopping.server;

import com.tul.shopping.service.UserService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tul.shopping"})
@EnableJpaRepositories ("com.tul.shopping.repository")
public class UserServer {
    private static final Logger LOG = LoggerFactory.getLogger(UserServer.class);
    private Server server;

    public void startServer() throws IOException {
        int port = 50051;
        try {
            server = ServerBuilder.forPort(port)
                    .addService(new UserService())
                    .build()
                    .start();
            LOG.info("Server started on port 50051");

            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    LOG.info("Clean server shutdown in case JVM was shutdown");
                    try {
                        UserServer.this.stopServer();
                    } catch (InterruptedException exception) {
                        LOG.error("Server shutdown interrupted", exception);
                    }
                }
            });
        } catch (IOException exception) {
            LOG.error("Server did not start", exception);
        }
    }

    public void stopServer() throws InterruptedException {
        if (server != null){
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null){
            server.awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        UserServer userServer = new UserServer();
        userServer.startServer();
        userServer.blockUntilShutdown();
    }
}

