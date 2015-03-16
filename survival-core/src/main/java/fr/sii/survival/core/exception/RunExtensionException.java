package fr.sii.survival.core.exception;

import fr.sii.survival.core.domain.extension.Developer;

public class RunExtensionException extends ExtensionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 770458822905413551L;

	public RunExtensionException(String message, Developer developer, Throwable cause) {
		super(message, developer, cause);
	}

}
