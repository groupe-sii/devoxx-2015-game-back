package fr.sii.survival.core.exception;

public class GameException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3116991673803569976L;

	public GameException(String message) {
		super(message);
	}

	public GameException(Throwable cause) {
		super(cause);
	}

	public GameException(String message, Throwable cause) {
		super(message, cause);
	}

}