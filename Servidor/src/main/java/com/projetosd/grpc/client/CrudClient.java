package com.projetosd.grpc.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.projetosd.grpc.CrudServiceGrpc;
import com.projetosd.grpc.resources.Configuration;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CrudClient {
	private static Properties connection;
	private static String serverAddress;
	private static int port;
	
	private static Thread menuThread;
	private static Thread responseThread;
	
	private ManagedChannel channel;
	private CrudServiceGrpc.CrudServiceBlockingStub stub;
	private BlockingQueue<Object> responseQueue = new LinkedBlockingQueue<Object>();
	
	public CrudClient(String argsPort) throws IOException {
		connection = Configuration.getProperties();
		serverAddress = connection.getProperty("properties.server.host");

		if (argsPort.equals("p1")) port = Integer.parseInt(connection.getProperty("properties.server.portOne"));
		if (argsPort.equals("p2")) port = Integer.parseInt(connection.getProperty("properties.server.portTwo"));
		if (argsPort.equals("p3")) port = Integer.parseInt(connection.getProperty("properties.server.portThree"));

		channel = ManagedChannelBuilder.forAddress(serverAddress, port).usePlaintext().build();
		stub = CrudServiceGrpc.newBlockingStub(channel);
		
		menuThread = new Thread(new MenuThread(stub, responseQueue));
		menuThread.setDaemon(true);
		
		responseThread = new Thread(new ResponseThread(responseQueue));
		responseThread.setDaemon(true);

		System.out.println("Comunicarei em: " + serverAddress + ":" + port);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		String[] ports = {"p1", "p2", "p3"};
		if(!args[0].isEmpty() && Arrays.asList(ports).contains(args[0])) {
			System.out.println("Porta enviada '" + args[0] + "' é válida.");
			final CrudClient client = new CrudClient(args[0]);
			client.start();
			client.blockUntilShutdown();
			client.stop();
		} else {
			System.out.println("Porta inválida.");
			System.exit(1);
		}
	}

	private void start() throws InterruptedException {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {	
				CrudClient.this.stop();
			}
		});		
		
		menuThread.start();
		responseThread.start();
	}

	@SuppressWarnings("deprecation")
	private void stop() {
		System.out.println("The gRPC client is terminating");
		
		if (responseThread.isAlive()) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("Failed! The server not send response");
				e.printStackTrace();
			} finally {
				responseThread.stop();
			}
		}
				
		if (channel != null) {
			channel.shutdown();
		}

		System.out.println("The gRPC client is finished");
	}
	
	private void blockUntilShutdown() throws InterruptedException {
		menuThread.join();
	}

}
