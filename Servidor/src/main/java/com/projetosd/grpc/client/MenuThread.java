package com.projetosd.grpc.client;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

import com.projetosd.grpc.*;

public class MenuThread implements Runnable {
	private CrudServiceGrpc.CrudServiceBlockingStub stub;
	private BlockingQueue<Object> responseQueue;
	private static Scanner scn;
	private Object output;
	
	public MenuThread(CrudServiceGrpc.CrudServiceBlockingStub _stub, BlockingQueue<Object> _responseQueue) {
		this.stub = _stub;
		this.responseQueue = _responseQueue;
	}

	public void run() {
		scn = new Scanner(System.in);
		System.out.println("\n------------ Commands List -------------");
		System.out.println("1. set <id> <value>");
		System.out.println("2. get <id>");
		System.out.println("3. get *");
		System.out.println("4. testandset <id> <value>");
		System.out.println("5. del <id>");
		System.out.println("6. Exit");
		System.out.println("------------------------------------------");
		
		while (true) {
			String input = null;
			
			if (!scn.hasNext())
				input = "Exit";
			else
				input = scn.nextLine();
			
			if (input.toLowerCase().equals("exit"))
				break;
			else {
				output = validateCommand(input);
				
				if (output != null)
					 responseQueue.add(output);
				else
					System.out.println("Resposta: Comando incorreto!\n");
			}
		}
		scn.close();
	}

	public Object validateCommand(String text) {
		Long id = Long.parseLong("0");
		text = text.trim();
		
		if(text.indexOf(' ') != -1) {
			String command = text.substring(0, text.indexOf(' '));
			String content = text.substring(text.indexOf(' ') + 1).trim();

			if (content.length() == 0)
				return null;

			switch (command.toLowerCase()) {
				case "set":
					if(content.indexOf(' ') != -1) {
						try {
							id = Long.parseLong(content.substring(0, content.indexOf(' ')));
							content = content.substring(content.indexOf(' ') + 1).trim();

							if (content.length() == 0)
								return null;

							return stub.set(SetRequest.newBuilder().setId(id).setContent(content).build());
						} catch (NumberFormatException e) {
							return null;
						}
					}
					break;
					
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
						return stub.del(DelRequest.newBuilder().setId(Long.parseLong(content)).build());
					} catch (NumberFormatException e) {
						return null;
					}
					
				case "testandset":
					if(content.indexOf(' ') != -1) {
						try {
							id = Long.parseLong(content.substring(0, content.indexOf(' ')));
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