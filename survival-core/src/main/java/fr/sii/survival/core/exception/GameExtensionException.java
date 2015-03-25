package fr.sii.survival.core.exception;

import fr.sii.survival.core.domain.extension.Developer;

public class GameExtensionException extends GameException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1268152636101497027L;
	
	private final Developer developer;
	
	public GameExtensionException(String message, Developer developer, Throwable cause) {
		super(message, cause);
		this.developer = developer;
	}

	public GameExtensionException(String message, Developer developer) {
		super(message);
		this.developer = developer;
	}

	public GameExtensionException(String message, Throwable cause) {
		super(message, cause);
		this.developer = null;
	}

	public GameExtensionException(String message) {
		super(message);
		this.developer = null;
	}

	public Developer getDeveloper() {
		return developer;
	}
}
