package com.projetosd.grpc.server;

import com.projetosd.grpc.resources.Input;

import java.util.concurrent.BlockingQueue;


public class OrganizerThread implements Runnable {
	private BlockingQueue<Input> receptionQueue;
	private BlockingQueue<Input> executionQueue;
	private BlockingQueue<Input> logQueue;
	private BlockingQueue<Input> repassQueue;
	private Input input;
	
	public OrganizerThread(
			BlockingQueue<Input> _receptinQueue, 
			BlockingQueue<Input> _executionQueue, 
			BlockingQueue<Input> _logQueue, 
			BlockingQueue<Input> _repassQueue) {
		
		this.receptionQueue = _receptinQueue;		
		this.executionQueue = _executionQueue;
		this.logQueue = _logQueue;
		this.repassQueue = _repassQueue;
	}

	public void run() {
		while(true) {
			try {
				input = receptionQueue.take();
				if (serverVerification(input)) {
					executionQueue.add(input);
					logQueue.add(input);
				} else {
					repassQueue.add(input);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private boolean serverVerification(Input input) {
		/*
		 * Método para verificação do servidor responsável
		 */
		return true;
	}
}
