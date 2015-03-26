package fr.sii.survival.core.service.message;

import fr.sii.survival.core.domain.message.GameMessage;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.message.MessageListenerRegistry;

public interface MessageService extends MessageListenerRegistry {
	/**
	 * Register an error that will be sent to the client
	 * 
	 * @param e
	 *            the error
	 */
	public void addError(GameException e);

	/**
	 * Register a message that will be sent to the client
	 * 
	 * @param message
	 *            the message
	 */
	public void addMessage(GameMessage message);
}
