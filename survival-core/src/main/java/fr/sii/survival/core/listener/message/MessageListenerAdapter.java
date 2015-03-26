package fr.sii.survival.core.listener.message;

import fr.sii.survival.core.domain.message.GameMessage;
import fr.sii.survival.core.exception.GameException;

public class MessageListenerAdapter implements MessageListener {

	@Override
	public void error(GameException e) {
		// nothing to do
	}

	@Override
	public void message(GameMessage message) {
		// nothing to do
	}
}
