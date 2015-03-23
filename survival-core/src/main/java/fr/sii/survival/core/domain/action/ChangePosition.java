package fr.sii.survival.core.domain.action;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
		return new HashCodeBuilder().append(start).append(end).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof ChangePosition)) {
			return false;
		}
		ChangePosition other = (ChangePosition) obj;
		return new EqualsBuilder().append(start, other.start).append(end, other.end).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangePosition [start=").append(start).append(", end=").append(end).append("]");
		return builder.toString();
	}
}
