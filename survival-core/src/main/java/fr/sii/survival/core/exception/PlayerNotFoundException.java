package fr.sii.survival.core.exception;

public class PlayerNotFoundException extends GameException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9088093967447334921L;

	public PlayerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlayerNotFoundException(String message) {
		super(message);
	}

	public PlayerNotFoundException(Throwable cause) {
		super(cause);
	}
}
