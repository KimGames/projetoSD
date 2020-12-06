import com.projetosd.grpc.*;
import com.projetosd.grpc.client.ResponseThread;
import com.projetosd.grpc.resources.Configuration;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestEstressCrud {
    private static Properties connection;
    private static String serverAddress;
    private static int port;

    private static Thread menuThread;
    private static Thread responseThread;

    private ManagedChannel channel;
    private CrudServiceGrpc.CrudServiceBlockingStub stub;
    private BlockingQueue<Object> responseQueue = new LinkedBlockingQueue<Object>();

    public TestEstressCrud() throws IOException {
        connection = Configuration.getProperties();
        serverAddress = connection.getProperty("properties.server.host");
        port = Integer.parseInt(connection.getProperty("properties.server.port"));

        channel = ManagedChannelBuilder.forAddress(serverAddress, port).usePlaintext().build();
        stub = CrudServiceGrpc.newBlockingStub(channel);

        responseThread = new Thread(new ResponseThread(responseQueue));
        responseThread.setDaemon(true);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final TestEstressCrud client = new TestEstressCrud();
        client.start();
        client.blockUntilShutdown();
        client.stop();
    }

    private void start() throws InterruptedException {

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                TestEstressCrud.this.stop();
            }
        });

        menuThread = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("Started");

                for(int i = 0; i < 1000; i++) {
                    UUID uuid = UUID.randomUUID();
                    String myRandom = uuid.toString();

                    System.out.println("Inserindo o valor " + myRandom.substring(0,24));

                    String setInput = "set " + myRandom.substring(0,24);
                    Object output = validateCommand(setInput);
                    responseQueue.add(output);
                    System.out.println("---------------------------");

                    String response = String.valueOf(output);
                    if(response.toLowerCase().contains("key")) {
                        int id = Integer.parseInt(response.substring(response.indexOf("=") + 1).replaceAll("\\D+",""));

                        if(id != 0) {
                            System.out.println("Recuperando pelo ID:");
                            String getInput = "get " + id;
                            Object getOutput = validateCommand(getInput);
                            responseQueue.add(getOutput);
                            System.out.println(getOutput);
                            System.out.println("---------------------------");

                            System.out.println("Atualizando pelo ID:");

                            UUID uuidupd = UUID.randomUUID();
                            String myRandomupd = uuidupd.toString();

                            System.out.println("Send " + myRandomupd.substring(0,24));

                            String updInput = "testandset " + id + " " + myRandomupd.substring(0,24);
                            Object updOutput = validateCommand(updInput);
                            responseQueue.add(updOutput);
                            System.out.println("---------------------------");

                            System.out.println("Recuperando pelo ID:");
                            String getInputTwo = "get " + id;
                            Object getOutputTwo = validateCommand(getInputTwo);
                            responseQueue.add(getOutputTwo);
                            System.out.println("---------------------------");

                            System.out.println("Deletando pelo ID:");
                            String delInput = "del " + id;
                            Object delOutput = validateCommand(delInput);
                            responseQueue.add(delOutput);
                            System.out.println("---------------------------");
                        } else {
                            System.out.println("ID igual a 0, erro.");
                        }
                    }
                }
            }
        });
        menuThread.setDaemon(true);
        menuThread.start();

        responseThread.start();
    }

    @SuppressWarnings("deprecation")
    private void stop() {
        System.out.println("The gRPC client is terminating");

        if (channel != null) {
            channel.shutdown();
        }

        System.out.println("The gRPC client is finished");
    }

    private void blockUntilShutdown() throws InterruptedException {
        menuThread.join();
    }

    public Object validateCommand(String text) {
        text = text.trim();

        if(text.indexOf(' ') != -1) {
            String command = text.substring(0, text.indexOf(' '));
            String content = text.substring(text.indexOf(' ') + 1).trim();

            if (content.length() == 0)
                return null;

            switch (command.toLowerCase()) {
                case "set":
                    return stub.set(SetRequest.newBuilder().setContent(content).build());

                case "get":
                    if (content.compareTo("*") != 0) {
                        try {
                            return stub.get(GetRequest.newBuilder().setId(Long.parseLong(content)).build());
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    } else {
                        return stub.get(GetRequest.newBuilder().setAll(true).build());
                    }

                case "del":
                    try {
                        return stub.del(DelRequest.newBuilder().build().newBuilder().setId(Long.parseLong(content)).build());
                    } catch (NumberFormatException e) {
                        return null;
                    }

                case "testandset":
                    if(content.indexOf(' ') != -1) {
                        try {
                            Long id = Long.parseLong(content.substring(0, content.indexOf(' ')));
                            content = content.substring(content.indexOf(' ') + 1).trim();

                            if (content.length() == 0)
                                return null;

                            return stub.testAndSet(TestAndSetRequest.newBuilder().setId(id).setContent(content).build());
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    }
                    break;
            }
        }
        return null;
    }
}
