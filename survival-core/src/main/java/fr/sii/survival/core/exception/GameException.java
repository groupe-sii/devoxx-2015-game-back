package fr.sii.survival.core.exception;

import fr.sii.survival.core.domain.extension.Developer;

public class GameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1268152636101497027L;
	
	private Developer developer;
	
	public GameException(String message, Developer developer, Throwable cause) {
		super(message, cause);
		this.developer = developer;
	}

	public GameException(String message, Developer developer) {
		super(message);
		this.developer = developer;
	}

	public GameException(String message, Throwable cause) {
		super(message, cause);
	}

	public GameException(String message) {
		super(message);
	}

	public Developer getDeveloper() {
		return developer;
	}
}
