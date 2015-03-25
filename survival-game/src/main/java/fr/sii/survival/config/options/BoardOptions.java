package fr.sii.survival.config.options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BoardOptions {
	/**
	 * The width of the board
	 */
	private final int width;
	
	/**
	 * The height of the board
	 */
	private final int height;

	@Autowired
	public BoardOptions(@Value("${game.board.width}") int width, @Value("${game.board.height}") int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
