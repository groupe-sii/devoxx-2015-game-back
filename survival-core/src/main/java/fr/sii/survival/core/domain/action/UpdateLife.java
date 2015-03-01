package fr.sii.survival.core.domain.action;

import fr.sii.survival.core.domain.board.Cell;

public class UpdateLife implements Action {
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

	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	@Deprecated
	public UpdateLife() {
		super();
	}

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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cell == null) ? 0 : cell.hashCode());
		result = prime * result + increment;
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
		UpdateLife other = (UpdateLife) obj;
		if (cell == null) {
			if (other.cell != null)
				return false;
		} else if (!cell.equals(other.cell))
			return false;
		if (increment != other.increment)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateLife [increment=").append(increment).append(", cell=").append(cell).append("]");
		return builder.toString();
	}
}
