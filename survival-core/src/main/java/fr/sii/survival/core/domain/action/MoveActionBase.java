package fr.sii.survival.core.domain.action;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.sii.survival.core.domain.board.Cell;

public class MoveActionBase implements Action {

	/**
	 * The start position on the game board
	 */
	protected Cell start;
	/**
	 * The end position on the game board
	 */
	protected Cell end;

	public MoveActionBase(Cell start, Cell end) {
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
		} else if (obj == null || !(obj instanceof MoveActionBase)) {
			return false;
		}
		MoveActionBase other = (MoveActionBase) obj;
		return new EqualsBuilder().append(start, other.start).append(end, other.end).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MoveActionBase [start=").append(start).append(", end=").append(end).append("]");
		return builder.toString();
	}
}