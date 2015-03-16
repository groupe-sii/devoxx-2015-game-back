package fr.sii.survival.core.service.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.message.Message;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.message.MessageListener;
import fr.sii.survival.core.listener.message.MessageListenerManager;

public class SimpleMessageService implements MessageService {
	private static final Logger logger = LoggerFactory.getLogger(SimpleMessageService.class);

	MessageListenerManager listenerManager;
	
	public SimpleMessageService(MessageListenerManager listenerManager) {
		super();
		this.listenerManager = listenerManager;
	}

	@Override
	public void addError(GameException e) {
		logger.error(e.getMessage());
		listenerManager.triggerError(e);
	}

	@Override
	public void addMessage(Message message) {
		switch (message.getLevel()) {
			case DEBUG:
				logger.debug(message.getMessage());
			break;
			case INFO:
				logger.info(message.getMessage());
			break;
			case WARNING:
				logger.warn(message.getMessage());
			break;
		}
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
