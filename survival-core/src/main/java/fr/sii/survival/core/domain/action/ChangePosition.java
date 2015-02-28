package fr.sii.survival.core.domain.action;

import fr.sii.survival.core.domain.board.Cell;

public class ChangePosition implements Action {
	/**
	 * The start position of the player to move on the game board
	 */
	private Cell start;

	/**
	 * The end position of the player to move on the game board
	 */
	private Cell end;

	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	public ChangePosition() {
		super();
	}

	public ChangePosition(Cell start, Cell end) {
		super();
		this.start = start;
		this.end = end;
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
