package fr.sii.survival.core.exception;

public class GameNotFoundException extends GameException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3795590557855714542L;

	public GameNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public GameNotFoundException(String message) {
		super(message);
	}

	public GameNotFoundException(Throwable cause) {
		super(cause);
	}

}
