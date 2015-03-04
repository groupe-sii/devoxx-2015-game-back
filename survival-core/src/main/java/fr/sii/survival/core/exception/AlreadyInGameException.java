package fr.sii.survival.core.exception;

public class AlreadyInGameException extends GameException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7674773070969091783L;

	public AlreadyInGameException(String message) {
		super(message);
	}

	public AlreadyInGameException(String message, Throwable cause) {
		super(message, cause);
	}

}
