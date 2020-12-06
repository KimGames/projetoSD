package com.projetosd.grpc.client;

import java.util.concurrent.BlockingQueue;

public class ResponseThread implements Runnable {
	private BlockingQueue<Object> responseQueue;
	
	public ResponseThread(BlockingQueue<Object> _responseQueue) {
		this.responseQueue = _responseQueue;
	}
	
    public void run() {
		while (true) {
			try {
				if (!responseQueue.isEmpty())
					System.out.println(responseQueue.take().toString().replace("response: ", "Resposta: "));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}
