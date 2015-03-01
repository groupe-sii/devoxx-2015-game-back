package fr.sii.survival.core.service.message;

import fr.sii.survival.core.domain.message.Message;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.message.MessageListener;
import fr.sii.survival.core.listener.message.MessageListenerManager;

public class SimpleMessageService implements MessageService {

	MessageListenerManager listenerManager;
	
	public SimpleMessageService(MessageListenerManager listenerManager) {
		super();
		this.listenerManager = listenerManager;
	}

	@Override
	public void addError(GameException e) {
		listenerManager.triggerError(e);
	}

	@Override
	public void addMessage(Message message) {
		listenerManager.triggerMessage(message);
	}
	
	@Override
	public void addMessageListener(MessageListener listener) {
		listenerManager.addMessageListener(listener);
	}

	@Override
	public void removeMessageListener(MessageListener listener) {
		listenerManager.removeMessageListener(listener);
	}
}
