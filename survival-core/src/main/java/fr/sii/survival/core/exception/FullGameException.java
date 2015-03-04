package fr.sii.survival.core.exception;

public class FullGameException extends GameException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7674773070969091783L;

	public FullGameException(String message) {
		super(message);
	}

	public FullGameException(String message, Throwable cause) {
		super(message, cause);
	}

}
