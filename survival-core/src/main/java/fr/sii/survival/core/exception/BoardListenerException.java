package fr.sii.survival.core.exception;

import fr.sii.survival.core.domain.extension.Developer;

public class BoardListenerException extends GameExtensionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8077629957688261880L;

	public BoardListenerException(String message, Developer developer, Throwable cause) {
		super(message, developer, cause);
	}
}
