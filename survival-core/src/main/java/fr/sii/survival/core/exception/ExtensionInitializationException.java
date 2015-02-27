package fr.sii.survival.core.exception;

import fr.sii.survival.core.domain.extension.Developer;

public class ExtensionInitializationException extends ExtensionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7462623888321656346L;

	public ExtensionInitializationException(String message, Developer developer, Throwable cause) {
		super(message, developer, cause);
	}

}
