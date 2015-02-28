package fr.sii.survival.core.listener.message;

import java.util.HashMap;
import java.util.Map;

import fr.sii.survival.core.domain.message.Message;
import fr.sii.survival.core.exception.GameException;

// TODO: manage locks to avoid concurrentmodifexception
public class SimpleMessageListenerManager implements MessageListenerManager {

	private Map<String, MessageListener> listeners;
	
	public SimpleMessageListenerManager() {
		this(new HashMap<>());
	}
	
	public SimpleMessageListenerManager(Map<String, MessageListener> listeners) {
		super();
		this.listeners = listeners;
	}

	@Override
	public void addMessageListener(MessageListener listener) {
		String key = getKey(listener);
		if(!listeners.containsKey(key)) {
			listeners.put(key, listener);
		} else {
			throw new IllegalArgumentException("Error listener can't be registered, same listener is already registered. key="+key);
		}
	}

	@Override
	public void removeMessageListener(MessageListener listener) {
		String key = getKey(listener);
		if(listeners.containsKey(key)) {
			listeners.remove(key);
		}
	}

	@Override
	public void triggerError(GameException exception) {
		for(MessageListener listener : listeners.values()) {
			try {
				listener.error(exception);
			} catch(Throwable e) {
				// TODO: log ?
			}
		}
	}

	@Override
	public void triggerMessage(Message message) {
		for(MessageListener listener : listeners.values()) {
			try {
				listener.message(message);
			} catch(Throwable e) {
				// TODO: log ?
			}
		}
	}

	private String getKey(MessageListener listener) {
		return listener.getClass().toString();
	}
}
