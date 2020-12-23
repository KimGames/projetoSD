package com.projetosd.grpc.server;

import com.projetosd.grpc.resources.Input;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;



public class LogThread implements Runnable {
	private final BlockingQueue<Input> logQueue;
	private HashMap<BigInteger, byte[]> dataBase;
	private String logFolder;
	
	private FileHandler logHandler = null;
    private Logger logger = null;
	
	private Input input;
	
	private Timer timer;
	
	private int logNumber;
	private int snapshotNumber;

    public LogThread(
    		BlockingQueue<Input> _logQueue, 
    		HashMap<BigInteger, byte[]> _dataBase,
    		String _logFolder, 
    		int _logNumber, 
    		int _snapshotNumber) {
        this.logQueue = _logQueue;
        this.logFolder = _logFolder;
        this.dataBase = _dataBase;
        
        this.logNumber = _logNumber;
        this.snapshotNumber = _snapshotNumber;
        
        logger = Logger.getLogger("Logger");
        
        timer = new Timer();
        timer.schedule(new Task(), 0, 20000);
    }

    public void run() {
    	Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				LogThread.this.stop();
			}
		});
    	
    	if (logger != null) {
    		while (true) {
				try {
					input = logQueue.take();

					if(input.getOperation() != 1) {
						logger.info(input.toString());
					}
				} catch (InterruptedException e) {
					logger.warning("Falha ao recuperar o comando a partir da fila de execução.");
				}
			}
    	}
    }
    
    public void stop() {  
    	timer.cancel();
    	
    	if (logHandler != null) {
    		logger.removeHandler(logHandler);
    		logHandler.close();
    	}
    }
    
    class Task extends TimerTask {
        public void run() {
        	try {
        		if (logHandler != null) {
            		logger.removeHandler(logHandler);
            		logHandler.close();
            	}
        		logHandler = new FileHandler(logFolder + "/" + "log." + logNumber++, true);
        		logHandler.setFormatter(new SimpleFormatter());
		        logger.addHandler(logHandler);
		        		        		        
		        File snapshotFile = new File(logFolder + "/" + "snap." + snapshotNumber++);
		        FileOutputStream fileOutputStream = new FileOutputStream(snapshotFile);
		        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

		        objectOutputStream.writeObject(dataBase);
		        objectOutputStream.flush();
		        objectOutputStream.close();
		        fileOutputStream.close();
        	    
			} catch (SecurityException | IOException e) {
				System.out.println("Houve uma falha ao registrar o estado do servidor nos arquivos de log e snapshot.");
			}
        }
    }
}
