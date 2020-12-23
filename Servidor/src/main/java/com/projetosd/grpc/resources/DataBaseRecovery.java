package com.projetosd.grpc.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

public class DataBaseRecovery {
	@SuppressWarnings("unchecked")
	public static HashMap<BigInteger, byte[]> dataBaseRecovery(
			BlockingQueue<Input> _executionQueue, String _logFolder, 
			int _logNumber, int _snapshotNumber) throws IOException {
		
		HashMap<BigInteger, byte[]> dataBase = new HashMap<BigInteger, byte[]>();
		
		String logFileName;
		File logFile, snapshotFile;
		
		if ((snapshotFile = new File(_logFolder + "/snap." + _snapshotNumber)).exists()) {
	        FileInputStream fileInputStream =new FileInputStream(snapshotFile);
	        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

	        try {
				dataBase = (HashMap<BigInteger, byte[]>)objectInputStream.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        objectInputStream.close();
	        fileInputStream.close();
	        
	        System.out.println("Estado da base de dados recuperado a partir do arquivo: " + snapshotFile.getName());
		}
		
    	if ((logFile = new File(logFileName = _logFolder + "/log." + _logNumber)).exists()) {    		
	    	BufferedReader log = new BufferedReader(new FileReader(logFileName));
	    	String line = new String(Files.readAllBytes(Paths.get(logFileName)), StandardCharsets.UTF_8);
	    	
	    	while ((line = log.readLine()) != null) {
	    		if (line.contains("INFO:")) {
	    			line = line.replace("INFO: " , "");
	    			
	    			String operation = line.substring(0, line.indexOf(':'));	    			
	    			line = line.replace(operation+":", "");
	    			
	    			String id = line.substring(0, line.indexOf(':'));	    			
	    			line = line.replace(id+":", "");
	    				    			
	    			String content = line;
	    			
	    			Input input = new Input();
	    			
	    			input.setOperation(Integer.parseInt(operation));
					input.setId(BigInteger.valueOf(Integer.parseInt(id)));
					input.setContent(content);

	    			_executionQueue.add(input);
	    		}
	    	}
	    	log.close();
	    	
	    	System.out.println("Comandos recuperados a partir do arquivo: " + logFile.getName());
    	}
    	
    	return dataBase;
    }
	
	public static int getFileNumber(String _logFolder, String _fileName) {
		File folder = new File(_logFolder);
        File[] listOfFiles = folder.listFiles();
        
        if (_fileName.compareTo("snap") != 0 && _fileName.compareTo("log") != 0) {
        	return -1;
        }
        
        int number = (_fileName.compareTo("snap") == 0) ? 1 : 0;
        
        String file;
        String[] fileParsedName;
        
        for (int i = 0; i < listOfFiles.length; i++) {
        	if (listOfFiles[i].isFile() 
        			&& (file = listOfFiles[i].getName()).contains(_fileName + ".")
        			&& (fileParsedName = file.split("\\.")).length == 2
        			&& Integer.parseInt(fileParsedName[1]) > number) {
        				number = Integer.parseInt(fileParsedName[1]);
        			}
        }       
		
		return number;
	}
}
