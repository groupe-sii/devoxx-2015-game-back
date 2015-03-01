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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChangePosition other = (ChangePosition) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangePosition [start=").append(start).append(", end=").append(end).append("]");
		return builder.toString();
	}
}
