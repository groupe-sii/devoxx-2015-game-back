package fr.sii.survival.core.listener.error;

import fr.sii.survival.core.exception.GameException;

public interface ErrorListener {
	public void error(GameException e);
}
