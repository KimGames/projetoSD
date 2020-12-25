package com.projetosd.grpc.server;

import org.apache.ratis.client.RaftClient;
import org.apache.ratis.conf.Parameters;
import org.apache.ratis.conf.RaftProperties;
import org.apache.ratis.grpc.GrpcConfigKeys;
import org.apache.ratis.grpc.GrpcFactory;
import org.apache.ratis.protocol.*;
import org.apache.ratis.server.RaftServer;
import org.apache.ratis.server.RaftServerConfigKeys;
import org.apache.ratis.thirdparty.com.google.protobuf.ByteString;
import org.apache.ratis.util.LifeCycle;

import com.projetosd.grpc.resources.Configuration;
import com.projetosd.grpc.resources.DataBaseRecovery;
import com.projetosd.grpc.resources.Input;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import com.projetosd.grpc.services.CrudServiceGrpcImpl;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CrudServer {

    private static Properties connection;
    private static int port;
    private static File logFolder;

    private static String raftLogPath;
    private static String raftGroupId;
    private static Integer raftPortOne;
    private static Integer raftPortTwo;
    private static Integer raftPortThree;

    private final Map<String,InetSocketAddress> id2addr;
    private final List<RaftPeer> raftAddresses;

    private final RaftGroup raftGroup;
    private RaftClient raftClient;
    private RaftServer raftServer;

    private Thread organizerThread;
    private Thread executionThread;
    private Thread logThread;
    private Server server;

    private BlockingQueue<Input> receptionQueue;
    private BlockingQueue<Input> executionQueue;
    private BlockingQueue<Input> logQueue;
    private BlockingQueue<Input> repassQueue;

    private HashMap<BigInteger, byte[]> dataBase;

    private int logNumber;
    private int snapshotNumber;

    public CrudServer() throws IOException {

        /*Mapeamento Inicial*/
        connection = Configuration.getProperties();
        port = Integer.parseInt(connection.getProperty("properties.server.port"));
        logFolder = new File(connection.getProperty("properties.server.logFolder"));

        /*Mapeamento para o RATIS*/
        raftLogPath = String.valueOf(connection.getProperty("properties.server.raft.logPath"));
        raftGroupId = String.valueOf(connection.getProperty("properties.server.raft.groupId"));
        raftPortOne = Integer.parseInt(connection.getProperty("properties.server.raft.portOne"));
        raftPortTwo = Integer.parseInt(connection.getProperty("properties.server.raft.portTwo"));
        raftPortThree = Integer.parseInt(connection.getProperty("properties.server.raft.portThree"));

        /*Configurações de Inicializacao do Peer do Ratis*/
        id2addr = new HashMap<>();
        id2addr.put("s1", new InetSocketAddress("127.0.0.1", raftPortOne));
        id2addr.put("s2", new InetSocketAddress("127.0.0.1", raftPortTwo));
        id2addr.put("s3", new InetSocketAddress("127.0.0.1", raftPortThree));

        raftAddresses = id2addr.entrySet()
                .stream()
                .map(e -> new RaftPeer(RaftPeerId.valueOf(e.getKey()), e.getValue()))
                .collect(Collectors.toList());

        raftGroup = RaftGroup.valueOf(
                RaftGroupId.valueOf(ByteString.copyFromUtf8(raftGroupId)),
                raftAddresses
        );

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
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        CrudServer crudServer = new CrudServer();

        /*Verifico se foi passada um servidor existente*/
        if(crudServer.validateRatisConfigs(args[0])) {
            System.out.println("Identificador enviado '" + args[0] + "' é válido.");

            crudServer.startRatisClient();
//            crudServer.startRatisServer(args[0]);
            crudServer.startRatisServer01();
            crudServer.startRatisServer02();
            crudServer.startRatisServer03();
            crudServer.startThreads();
            crudServer.start();
            crudServer.blockUntilShutdown();

        } else {
            System.out.println("Identificador enviado '" + args[0] + "' é inválido.");
            System.exit(1);
        }
    }

    private boolean validateRatisConfigs(String serverId) {
        RaftPeerId myId = RaftPeerId.valueOf(serverId);
        return raftAddresses.stream().anyMatch(p -> p.getId().equals(myId));
    }

    private void startRatisClient() {

        /*Mapeamento localhost e porta*/
        RaftProperties raftProperties = new RaftProperties();

        raftClient = RaftClient.newBuilder()
                .setProperties(raftProperties)
                .setRaftGroup(raftGroup)
                .setClientRpc(new GrpcFactory(new Parameters())
                        .newRaftClientRpc(ClientId.randomId(), raftProperties))
                .build();

    }

    private void startThreads() {
        organizerThread = new Thread(new OrganizerThread(receptionQueue, executionQueue, logQueue, repassQueue));
        executionThread = new Thread(new ExecutionThread(executionQueue, dataBase, raftClient));
        logThread = new Thread (new LogThread(logQueue, dataBase, logFolder.toString(), logNumber, snapshotNumber));
    }

    private void startRatisServer(String serverId) throws IOException {

        //Setup for this node.
        RaftPeerId myId = RaftPeerId.valueOf(serverId);

        RaftProperties properties = new RaftProperties();
        properties.setInt(GrpcConfigKeys.OutputStream.RETRY_TIMES_KEY, Integer.MAX_VALUE);
        GrpcConfigKeys.Server.setPort(properties, id2addr.get(serverId).getPort());
        RaftServerConfigKeys.setStorageDir(properties, Collections.singletonList(new File(raftLogPath + myId)));

        //Join the group of processes.
        raftServer = RaftServer.newBuilder()
                .setServerId(myId)
                .setStateMachine(new StateMachineServer()).setProperties(properties)
                .setGroup(raftGroup)
                .build();
        raftServer.start();
        System.out.println("raftServer " + serverId + " foi iniciado corretamente");
    }

    private void startRatisServer01() throws IOException {

        String serverId = "s1";

        //Setup for this node.
        RaftPeerId myId = RaftPeerId.valueOf(serverId);

        RaftProperties properties = new RaftProperties();
        properties.setInt(GrpcConfigKeys.OutputStream.RETRY_TIMES_KEY, Integer.MAX_VALUE);
        GrpcConfigKeys.Server.setPort(properties, id2addr.get(serverId).getPort());
        RaftServerConfigKeys.setStorageDir(properties, Collections.singletonList(new File(raftLogPath + myId)));

        //Join the group of processes.
        raftServer = RaftServer.newBuilder()
                .setServerId(myId)
                .setStateMachine(new StateMachineServer()).setProperties(properties)
                .setGroup(raftGroup)
                .build();
        raftServer.start();
        System.out.println("raftServer " + serverId + " foi iniciado corretamente");
    }
    private void startRatisServer02() throws IOException {

        String serverId = "s2";

        //Setup for this node.
        RaftPeerId myId = RaftPeerId.valueOf(serverId);

        RaftProperties properties = new RaftProperties();
        properties.setInt(GrpcConfigKeys.OutputStream.RETRY_TIMES_KEY, Integer.MAX_VALUE);
        GrpcConfigKeys.Server.setPort(properties, id2addr.get(serverId).getPort());
        RaftServerConfigKeys.setStorageDir(properties, Collections.singletonList(new File(raftLogPath + myId)));

        //Join the group of processes.
        raftServer = RaftServer.newBuilder()
                .setServerId(myId)
                .setStateMachine(new StateMachineServer()).setProperties(properties)
                .setGroup(raftGroup)
                .build();
        raftServer.start();
        System.out.println("raftServer " + serverId + " foi iniciado corretamente");
    }
    private void startRatisServer03() throws IOException {

        String serverId = "s3";

        //Setup for this node.
        RaftPeerId myId = RaftPeerId.valueOf(serverId);

        RaftProperties properties = new RaftProperties();
        properties.setInt(GrpcConfigKeys.OutputStream.RETRY_TIMES_KEY, Integer.MAX_VALUE);
        GrpcConfigKeys.Server.setPort(properties, id2addr.get(serverId).getPort());
        RaftServerConfigKeys.setStorageDir(properties, Collections.singletonList(new File(raftLogPath + myId)));

        //Join the group of processes.
        raftServer = RaftServer.newBuilder()
                .setServerId(myId)
                .setStateMachine(new StateMachineServer()).setProperties(properties)
                .setGroup(raftGroup)
                .build();
        raftServer.start();
        System.out.println("raftServer " + serverId + " foi iniciado corretamente");
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
