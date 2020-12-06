package com.projetosd.grpc.client;

import java.io.IOException;
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
	
	public CrudClient() throws IOException {
		connection = Configuration.getProperties();
		serverAddress = connection.getProperty("properties.server.host");
		port = Integer.parseInt(connection.getProperty("properties.server.port"));
		
		channel = ManagedChannelBuilder
	    		.forAddress(serverAddress, port).usePlaintext().build();
		stub = CrudServiceGrpc.newBlockingStub(channel);
		
		menuThread = new Thread(new MenuThread(stub, responseQueue));
		menuThread.setDaemon(true);
		
		responseThread = new Thread(new ResponseThread(responseQueue));
		responseThread.setDaemon(true);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		final CrudClient client = new CrudClient();
		client.start();
		client.blockUntilShutdown();
		client.stop();
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
