package fr.sii.survival.core.exception;

import fr.sii.survival.core.domain.extension.Developer;

public class ExtensionException extends GameException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7462623888321656346L;

	public ExtensionException(String message, Developer developer, Throwable cause) {
		super(message, developer, cause);
	}

}
