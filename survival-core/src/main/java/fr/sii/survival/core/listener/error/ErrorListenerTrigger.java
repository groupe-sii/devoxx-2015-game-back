package fr.sii.survival.core.listener.error;

import fr.sii.survival.core.exception.GameException;

public interface ErrorListenerTrigger {
	
	public void triggerError(GameException e);

}
