package com.projetosd.grpc.server;

import com.projetosd.grpc.resources.Configuration;
import com.projetosd.grpc.resources.DataBaseRecovery;
import com.projetosd.grpc.resources.Input;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import com.projetosd.grpc.services.CrudServiceGrpcImpl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class CrudServer {

    private static Properties connection;
    private static int port;
    private static File logFolder;

    private Thread organizerThread;
    private Thread executionThread;
    private Thread logThread;
    private Server server;

    private BlockingQueue<Input> receptionQueue;
    private BlockingQueue<Input> executionQueue;
    private BlockingQueue<Input> logQueue;
    private BlockingQueue<Input> repassQueue;

    private HashMap<Long, byte[]> dataBase;

    private int logNumber;
    private int snapshotNumber;

    public CrudServer() throws IOException {
        connection = Configuration.getProperties();
        port = Integer.parseInt(connection.getProperty("properties.server.port"));
        logFolder = new File(connection.getProperty("properties.server.logFolder"));

        if (!logFolder.exists()) {
            logFolder.mkdir();
        }

        receptionQueue = new LinkedBlockingQueue<Input>();
        executionQueue = new LinkedBlockingQueue<Input>();
        logQueue = new LinkedBlockingQueue<Input>();
        repassQueue = new LinkedBlockingQueue<Input>();

        logNumber = DataBaseRecovery.getFileNumber(logFolder.toString(), "log");
        snapshotNumber = DataBaseRecovery.getFileNumber(logFolder.toString(), "snap");

        if ((snapshotNumber - logNumber) > 1) {
            logNumber = this.snapshotNumber-1;
        } else if ((snapshotNumber - logNumber) < 1) {
            snapshotNumber = this.logNumber+1;
        }

        try {
            dataBase = DataBaseRecovery.dataBaseRecovery(executionQueue, logFolder.toString(), logNumber, snapshotNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        organizerThread = new Thread(new OrganizerThread(receptionQueue, executionQueue, logQueue, repassQueue));
        executionThread = new Thread(new ExecutionThread(executionQueue, dataBase));
        logThread = new Thread (new LogThread(logQueue, dataBase, logFolder.toString(), logNumber, snapshotNumber));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        CrudServer crudServer = new CrudServer();
        crudServer.start();
        crudServer.blockUntilShutdown();
    }

    private void start() throws IOException {

        executionThread.setDaemon(true);
        executionThread.start();

        while (executionQueue.size() != 0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        organizerThread.setDaemon(true);
        organizerThread.start();

        logThread.setDaemon(true);
        logThread.start();

        server = ServerBuilder.forPort(port).addService(new CrudServiceGrpcImpl(receptionQueue)).build().start();

        System.out.println("Server started, listening on " + port);
        System.out.println("Press Ctrl+c to exit");

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("The gRPC server is terminating");
                CrudServer.this.stop();
                System.out.println("The gRPC server is finished");
            }
        });
    }

    private void stop() {
        if (server != null) server.shutdown();

        while(receptionQueue.size() != 0
                || executionQueue.size() != 0
                || logQueue.size() != 0
                || repassQueue.size() != 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) server.awaitTermination();
    }
}
