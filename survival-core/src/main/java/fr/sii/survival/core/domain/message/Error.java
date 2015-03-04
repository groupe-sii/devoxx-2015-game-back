package fr.sii.survival.core.domain.message;

import fr.sii.survival.core.domain.extension.Developer;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.exception.GameExtensionException;

public class Error {
	private String message;
	
	private String cause;
	
	private Developer developer;

	public Error(Throwable e) {
		this(e.getMessage(), e.getCause()==null ? null : e.getCause().getMessage(), null);
	}
	
	public Error(GameException e) {
		this(e.getMessage(), e.getCause()==null ? null : e.getCause().getMessage(), e instanceof GameExtensionException ? ((GameExtensionException) e).getDeveloper() : null);
	}
	
	public Error(String message, String cause, Developer developer) {
		super();
		this.message = message;
		this.cause = cause;
		this.developer = developer;
	}

	public String getMessage() {
		return message;
	}

	public String getCause() {
		return cause;
	}

	public Developer getDeveloper() {
		return developer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[ERROR] ").append(message).append(". Cause:").append(cause).append(". developper=").append(developer);
		return builder.toString();
	}
}
