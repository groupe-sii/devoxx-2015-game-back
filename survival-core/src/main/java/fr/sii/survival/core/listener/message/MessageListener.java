package fr.sii.survival.core.listener.message;

import fr.sii.survival.core.domain.message.Message;
import fr.sii.survival.core.exception.GameException;

public interface MessageListener {
	public void error(GameException e);
	
	public void message(Message message);
}
