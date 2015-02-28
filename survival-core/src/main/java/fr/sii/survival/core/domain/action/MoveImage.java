package fr.sii.survival.core.domain.action;

import fr.sii.survival.core.domain.board.Cell;

public class MoveImage implements Action {
	/**
	 * The name of the image to move
	 */
	private String name;

	/**
	 * The start position of the image on the game board
	 */
	private Cell start;

	/**
	 * The end position of the image on the game board
	 */
	private Cell end;

	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	public MoveImage() {
		super();
	}

	public MoveImage(String name, Cell start, Cell end) {
		super();
		this.name = name;
		this.start = start;
		this.end = end;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Cell getStart() {
		return start;
	}

	public void setStart(Cell start) {
		this.start = start;
	}

	public Cell getEnd() {
		return end;
	}

	public void setEnd(Cell end) {
		this.end = end;
	}
}
