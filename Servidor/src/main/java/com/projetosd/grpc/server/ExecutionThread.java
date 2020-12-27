package com.projetosd.grpc.server;

import com.projetosd.grpc.resources.ActionsEnum;
import com.projetosd.grpc.resources.Input;
import org.apache.ratis.client.RaftClient;
import org.apache.ratis.protocol.Message;
import org.apache.ratis.protocol.RaftClientReply;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;


public class ExecutionThread implements Runnable {

	/*Fila de Execução*/
	private BlockingQueue<Input> executionQueue;

	/*Banco de dados e chave*/
	private HashMap<BigInteger, byte[]> dataBase;
	private BigInteger queryKey;

	/*Raft*/
	private RaftClient raftClient;
	private RaftClientReply getValue;

	/*Comando de entrada e resposta*/
	private Input input;
	private String response;

	
	public ExecutionThread(BlockingQueue<Input> _executionQueue, HashMap<BigInteger, byte[]> _dataBase,
						   RaftClient _raftClient) {
		this.executionQueue = _executionQueue;
		this.dataBase = _dataBase;
		this.raftClient = _raftClient;
	}
	
	public void run() {
		try {
			while (true) {

				input = executionQueue.take();
				ActionsEnum actionOperation = ActionsEnum.values()[input.getOperation()];

				switch (actionOperation) {
					/*Função set*/
					case SET:

						/*Recupero a chave informada*/
						queryKey = input.getId();

						/*Verifico se a chave já existe*/
						if (dataBase.containsKey(queryKey)) { /*Comparativo com id passado*/
							if (input.getEventSource() != null) {
								input.getEventSource().reply("(ERROR) Chave enviada ja possui valores");
							}
						} else {
							/*Adiciono o valor em meu RATIS*/
							getValue = raftClient.send(Message.valueOf("set:" + queryKey + ":" + input.getContent()));
							response = getValue.getMessage().getContent().toString(Charset.defaultCharset());
							System.out.println("Resposta:" + response);

							if (input.getEventSource() != null) {
								input.getEventSource().reply("(SUCCESS) " + queryKey + ":" + input.getContent());
							}
						}

						break;

					/*Função get*/
					case GET:
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

								getValue = raftClient.sendReadOnly(Message.valueOf("get:" + input.getId()));
								response = getValue.getMessage().getContent().toString(Charset.defaultCharset());
								System.out.println("Resposta:" + response);

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
					case TESTANDSET:
						if (dataBase.containsKey(input.getId())) { /*Comparativo com id passado*/

							/*Atualizo no ratis*/
							getValue = raftClient.send(Message.valueOf("testandset:" + input.getId() + ":" + input.getContent()));
							response = getValue.getMessage().getContent().toString(Charset.defaultCharset());
							System.out.println("Resposta:" + response);


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
					case DEL:
						if (dataBase.containsKey(input.getId())) { /*Comparativo com id passado*/

							/*Atualizo no ratis*/
							getValue = raftClient.send(Message.valueOf("del:" + input.getId()));
							response = getValue.getMessage().getContent().toString(Charset.defaultCharset());
							System.out.println("Resposta:" + response);

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
