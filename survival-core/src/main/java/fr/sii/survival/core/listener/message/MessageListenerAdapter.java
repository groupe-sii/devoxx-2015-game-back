package fr.sii.survival.core.listener.message;

import fr.sii.survival.core.domain.message.Message;
import fr.sii.survival.core.exception.GameException;

public class MessageListenerAdapter implements MessageListener {

	@Override
	public void error(GameException e) {
	}

	@Override
	public void message(Message message) {
	}
}
