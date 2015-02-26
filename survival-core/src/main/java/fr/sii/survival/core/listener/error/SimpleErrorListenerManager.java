package fr.sii.survival.core.listener.error;

import java.util.HashMap;
import java.util.Map;

import fr.sii.survival.core.exception.GameException;

// TODO: manage locks to avoid concurrentmodifexception
public class SimpleErrorListenerManager implements ErrorListenerManager {

	private Map<String, ErrorListener> listeners;
	
	public SimpleErrorListenerManager() {
		this(new HashMap<>());
	}
	
	public SimpleErrorListenerManager(Map<String, ErrorListener> listeners) {
		super();
		this.listeners = listeners;
	}

	@Override
	public void addErrorListener(ErrorListener listener) {
		String key = getKey(listener);
		if(!listeners.containsKey(key)) {
			listeners.put(key, listener);
		} else {
			throw new IllegalArgumentException("Error listener can't be registered, same listener is already registered. key="+key);
		}
	}

	@Override
	public void removeErrorListener(ErrorListener listener) {
		String key = getKey(listener);
		if(listeners.containsKey(key)) {
			listeners.remove(key);
		}
	}

	@Override
	public void triggerError(GameException exception) {
		for(ErrorListener listener : listeners.values()) {
			try {
				listener.error(exception);
			} catch(Throwable e) {
				// TODO: log ?
			}
		}
	}
	
	private String getKey(ErrorListener listener) {
		return listener.getClass().toString();
	}
}
