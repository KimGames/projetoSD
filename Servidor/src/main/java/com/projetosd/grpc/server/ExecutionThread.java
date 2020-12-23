package com.projetosd.grpc.server;

import com.projetosd.grpc.resources.Input;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.BlockingQueue;


public class ExecutionThread implements Runnable {

	private BlockingQueue<Input> executionQueue;
	
	private HashMap<BigInteger, byte[]> dataBase;

	private long actualKey;
	private BigInteger nextKey;

	private Input input;
	
	public ExecutionThread(BlockingQueue<Input> _executionQueue, HashMap<BigInteger, byte[]> _dataBase) {
		this.executionQueue = _executionQueue;
		this.dataBase = _dataBase;
		actualKey = dataBase.size();
		nextKey = BigInteger.valueOf(dataBase.size());
	}
	
	public void run() {
		try {
			while (true) {
				input = executionQueue.take();
				switch (input.getOperation()) {
					case 0:

						actualKey = actualKey + 1;
						nextKey = BigInteger.valueOf(actualKey);

						dataBase.put(nextKey, input.getContent().getBytes());

						if (input.getEventSource() != null) {
							input.getEventSource().reply("SUCCESS : Key = " + String.valueOf(nextKey));
						}

						break;
					case 1:
						if (input.getContent().compareTo("*") == 0) {
							String response = "";
							for (HashMap.Entry<BigInteger, byte[]> pair : dataBase.entrySet()) {
								response += "Key: " + pair.getKey().toString() + " Value: " + new String(pair.getValue()) + " | ";
							}
							if (input.getEventSource() != null) {
								input.getEventSource().reply(response);
							}
						} else if (dataBase.containsKey(input.getId())) {
							if (input.getEventSource() != null) {
								input.getEventSource().reply(input.getId() + " " + new String(dataBase.get(input.getId())));
							}
						} else {
							if (input.getEventSource() != null) {
								input.getEventSource().reply("ERROR");
							}
						}
						break;
					case 2:
						if (dataBase.containsKey(input.getId())) {
							dataBase.replace(input.getId(), input.getContent().getBytes());
							if (input.getEventSource() != null) {
								input.getEventSource().reply("ERROR");
							}
						} else {
							if (input.getEventSource() != null) {
								input.getEventSource().reply("ERROR");
							}
						}
						break;
					case 3:
						if (dataBase.containsKey(input.getId())) {
							dataBase.remove(input.getId());
							if (input.getEventSource() != null) {
								input.getEventSource().reply("SUCCESS");
							}
						} else {
							if (input.getEventSource() != null) {
								input.getEventSource().reply("ERROR");
							}
						}
						break;

					default:
						if (input.getEventSource() != null) {
							input.getEventSource().reply("ERROR");
						}
						break;
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Ocorreu uma falha na execuçã das requisições.\nO servidor será finalizado.");
		}
	}
}
