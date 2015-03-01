package fr.sii.survival.core.domain.message;

import fr.sii.survival.core.domain.extension.Developer;
import fr.sii.survival.core.exception.GameException;

public class Error {
	private String message;
	
	private String cause;
	
	private Developer developper;

	public Error(GameException e) {
		this(e.getMessage(), e.getCause()==null ? null : e.getCause().getMessage(), e.getDeveloper());
	}
	
	public Error(String message, String cause, Developer developper) {
		super();
		this.message = message;
		this.cause = cause;
		this.developper = developper;
	}

	public String getMessage() {
		return message;
	}

	public String getCause() {
		return cause;
	}

	public Developer getDevelopper() {
		return developper;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[ERROR] ").append(message).append(". Cause:").append(cause).append(". developper=").append(developper);
		return builder.toString();
	}
}
