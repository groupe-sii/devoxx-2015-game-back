package fr.sii.survival.core.service.error;

import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.error.ErrorListenerRegistry;

public interface ErrorService extends ErrorListenerRegistry {
	public void addError(GameException e);
}
