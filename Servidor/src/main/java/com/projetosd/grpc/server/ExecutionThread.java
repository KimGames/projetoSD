package com.projetosd.grpc.server;

import com.projetosd.grpc.resources.Input;
import org.apache.ratis.client.RaftClient;
import org.apache.ratis.protocol.Message;
import org.apache.ratis.protocol.RaftClientReply;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.BlockingQueue;


public class ExecutionThread implements Runnable {

	private BlockingQueue<Input> executionQueue;
	
	private HashMap<BigInteger, byte[]> dataBase;

	private long actualKey;
	private BigInteger nextKey;
	private RaftClient raftClient;

	private RaftClientReply getValue;
	private String response;

	private Input input;
	
	public ExecutionThread(BlockingQueue<Input> _executionQueue, HashMap<BigInteger, byte[]> _dataBase,
						   RaftClient _raftClient) {
		this.executionQueue = _executionQueue;
		this.dataBase = _dataBase;
		this.raftClient = _raftClient;
		actualKey = dataBase.size();
		nextKey = BigInteger.valueOf(dataBase.size());
	}
	
	public void run() {
		try {
			while (true) {
				input = executionQueue.take();
				switch (input.getOperation()) {
					/*Função set*/
					case 0:

						actualKey = actualKey + 1;
						nextKey = BigInteger.valueOf(actualKey);

						getValue = raftClient.send(Message.valueOf("add:" + nextKey + ":" + input.getContent()));
						response = getValue.getMessage().getContent().toString(Charset.defaultCharset());
						System.out.println("Resposta:" + response);

						dataBase.put(nextKey, input.getContent().getBytes());

						if (input.getEventSource() != null) {
							input.getEventSource().reply("(SUCCESS) " + nextKey + ":" + input.getContent());
						}

						break;

					/*Função get*/
					case 1:

						if (input.getContent().compareTo("*") == 0) { /*Comparativo com todos*/
							String response = "";
							for (HashMap.Entry<BigInteger, byte[]> pair : dataBase.entrySet()) {
								response += pair.getKey().toString() + ":" + new String(pair.getValue()) + " - ";
							}

							if (input.getEventSource() != null) {
								input.getEventSource().reply("(SUCCESS) " + response);
							}
						} else if (dataBase.containsKey(input.getId())) { /*Comparativo com id passado*/
							if (input.getEventSource() != null) {
								String response = input.getId() + ":" + new String(dataBase.get(input.getId()));
								input.getEventSource().reply("(SUCCESS) " + response);
							}
						} else { /*Não foi passado chave e/ou nao existe*/
							if (input.getEventSource() != null) {
								input.getEventSource().reply("(ERROR) Nao informado chave e/ou chave invalida");
							}
						}
						break;

					/*Função testandset*/
					case 2:
						if (dataBase.containsKey(input.getId())) { /*Comparativo com id passado*/

							/*Atualizo no ratis*/
							getValue = raftClient.send(Message.valueOf("add:" + input.getId() + ":" + input.getContent()));
							response = getValue.getMessage().getContent().toString(Charset.defaultCharset());
							System.out.println("Resposta:" + response);

							dataBase.replace(input.getId(), input.getContent().getBytes());
							if (input.getEventSource() != null) {
								input.getEventSource().reply("(SUCCESS) " + input.getId() + ":" + input.getContent());
							}
						} else { /*Não foi passado chave e/ou nao existe*/
							if (input.getEventSource() != null) {
								input.getEventSource().reply("(ERROR) Nao informado chave e/ou chave invalida");
							}
						}
						break;

					/*Função del*/
					case 3:
						if (dataBase.containsKey(input.getId())) { /*Comparativo com id passado*/
							dataBase.remove(input.getId());
							if (input.getEventSource() != null) {
								input.getEventSource().reply("(SUCCESS) " + input.getId() + " removido.");
							}
						} else { /*Não foi passado chave e/ou nao existe*/
							if (input.getEventSource() != null) {
								input.getEventSource().reply("(ERROR) Nao informado chave e/ou chave invalida");
							}
						}
						break;

					default:
						if (input.getEventSource() != null) {
							input.getEventSource().reply("(ERROR) Comando nao conhecido");
						}
						break;
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Ocorreu uma falha na execução das requisições.\nO servidor será finalizado.");
		} catch (IOException ioException) {
			System.out.println("ioException: " + ioException.getMessage());
		}
	}
}
