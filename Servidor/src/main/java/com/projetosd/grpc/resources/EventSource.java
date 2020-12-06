package com.projetosd.grpc.resources;

import java.util.ArrayList;
import java.util.function.Consumer;

public class EventSource {
    public interface Observer {
        void update(final String event);
    }
  
    private final ArrayList<Observer> observers = new ArrayList<>();
  
    private void notifyObservers(final String event) {
        observers.forEach(new Consumer<Observer>() {
			@Override
			public void accept(Observer observer) {
				observer.update(event);
			}
		});
    }
  
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
  
    public void reply(String _reply) {
    	notifyObservers(_reply);
    }
}
