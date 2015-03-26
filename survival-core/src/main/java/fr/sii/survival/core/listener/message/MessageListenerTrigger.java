package fr.sii.survival.core.listener.message;

import fr.sii.survival.core.domain.message.GameMessage;
import fr.sii.survival.core.exception.GameException;

public interface MessageListenerTrigger {
	
	public void triggerError(GameException e);

	public void triggerMessage(GameMessage message);
}
