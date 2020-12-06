package com.projetosd.grpc.resources;

import java.math.BigInteger;
import java.util.*; 

public class BlockingResponseMap { 
  
    private Map<BigInteger, String> map = new HashMap<BigInteger, String>();
    private int limit = 10; 
  
    public BlockingResponseMap(int limit) 
    { 
        this.limit = limit; 
    } 
  
    public synchronized void add(BigInteger id, String response) 
        throws InterruptedException 
    { 
        while (this.map.size() == this.limit) { 
            wait(); 
        } 
        if (this.map.size() == 0) { 
            notifyAll(); 
        } 
        this.map.put(id, response); 
    } 
  
    public synchronized String remove(BigInteger id) 
        throws InterruptedException 
    { 
        while (this.map.size() == 0) { 
            wait(); 
        } 
        if (this.map.size() == this.limit) { 
            notifyAll(); 
        } 
  
        return this.map.remove(id).toString(); 
    } 
}