package fr.sii.survival.config.options;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="game.board")
public class BoardOptions {
	/**
	 * The width of the board
	 */
	private int width;
	
	/**
	 * The height of the board
	 */
	private int height;

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
