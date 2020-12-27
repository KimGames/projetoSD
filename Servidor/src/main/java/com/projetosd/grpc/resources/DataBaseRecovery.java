package com.projetosd.grpc.resources;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

public class DataBaseRecovery {

	@SuppressWarnings("unchecked")
	public static HashMap<BigInteger, byte[]> dataBaseRecovery(
			BlockingQueue<Input> _executionQueue) throws IOException {
		
		HashMap<BigInteger, byte[]> dataBase = new HashMap<BigInteger, byte[]>();
    	return dataBase;
    }
}
