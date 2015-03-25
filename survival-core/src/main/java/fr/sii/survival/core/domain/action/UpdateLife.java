package fr.sii.survival.core.domain.action;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.sii.survival.core.domain.board.Cell;

public abstract class UpdateLife implements Action {
	/**
	 * The increment value to apply on the location. If increment is positive,
	 * then life will be incremented. If increment is negative, then life will
	 * be decremented.
	 */
	private int increment;

	/**
	 * The location of the game board cell where increment value should be
	 * applied
	 */
	private Cell cell;

	public UpdateLife(int increment, Cell cell) {
		super();
		this.increment = increment;
		this.cell = cell;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(increment).append(cell).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof UpdateLife)) {
			return false;
		}
		UpdateLife other = (UpdateLife) obj;
		return new EqualsBuilder().append(increment, other.increment).append(cell, other.cell).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateLife [increment=").append(increment).append(", cell=").append(cell).append("]");
		return builder.toString();
	}
}
