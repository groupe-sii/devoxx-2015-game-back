package fr.sii.survival.core.domain.message;

public class Message {
	/**
	 * The message to provide to the client
	 */
	private String message;

	/**
	 * The level of the message
	 */
	private Level level;

	public Message(String message, Level level) {
		super();
		this.message = message;
		this.level = level;
	}

	/**
	 * Provides an {@link Level}.INFO message to the client
	 * 
	 * @param message
	 *            the message to provide to the client
	 */
	public Message(String message) {
		this(message, Level.INFO);
	}

	public String getMessage() {
		return message;
	}

	public Level getLevel() {
		return level;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[").append(level).append("] ").append(message);
		return builder.toString();
	}
}
