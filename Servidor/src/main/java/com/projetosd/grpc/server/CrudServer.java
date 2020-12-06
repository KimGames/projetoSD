package com.projetosd.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import com.projetosd.grpc.service.CrudServiceGrpcImpl;

import java.io.IOException;
import java.util.logging.Logger;

public class CrudServer {
    private static final Logger LOG = Logger.getLogger(CrudServer.class.getName());
    private Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        CrudServer crudServer = new CrudServer();
        crudServer.start();
        crudServer.blockUntilShutdown();
    }

    private void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port).addService(new CrudServiceGrpcImpl()).build().start();
        LOG.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                CrudServer.this.stop();
            }
        });
    }

    private void stop() {
        if (server != null) server.shutdown();
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) server.awaitTermination();
    }
}
