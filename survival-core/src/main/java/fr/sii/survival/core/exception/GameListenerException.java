package fr.sii.survival.core.exception;

import fr.sii.survival.core.domain.extension.Developer;

public class GameListenerException extends GameExtensionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4234390914309348892L;

	public GameListenerException(String message, Developer developer, Throwable cause) {
		super(message, developer, cause);
	}

}
