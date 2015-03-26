package fr.sii.survival.core.listener.message;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.message.GameMessage;
import fr.sii.survival.core.exception.GameException;

// TODO: manage locks to avoid concurrentmodifexception
public class SimpleMessageListenerManager implements MessageListenerManager {

	private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageListenerManager.class);
	
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
			} catch(Exception e) {
				LOG.error("failed to trigger error event on listener {}. Cause: {}", listener.getClass().getName(), e);
			}
		}
	}

	@Override
	public void triggerMessage(GameMessage message) {
		for(MessageListener listener : listeners.values()) {
			try {
				listener.message(message);
			} catch(Exception e) {
				LOG.error("failed to trigger message event on listener {}. Cause: {}", listener.getClass().getName(), e);
			}
		}
	}

	private String getKey(MessageListener listener) {
		return listener.getClass().toString();
	}
}
