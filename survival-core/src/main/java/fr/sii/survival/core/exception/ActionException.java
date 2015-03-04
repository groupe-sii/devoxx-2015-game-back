package fr.sii.survival.core.exception;

import fr.sii.survival.core.domain.extension.Developer;

public class ActionException extends GameExtensionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1341467474676161054L;

	public ActionException(String message, Developer developer, Throwable cause) {
		super(message, developer, cause);
	}

	public ActionException(String message, Developer developer) {
		super(message, developer);
	}

	public ActionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ActionException(String message) {
		super(message);
	}

}
