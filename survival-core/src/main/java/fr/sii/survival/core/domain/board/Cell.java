package fr.sii.survival.core.domain.board;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Cell {
	/**
	 * The x position
	 */
	private int x;

	/**
	 * The y position
	 */
	private int y;

	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	@Deprecated
	public Cell() {
		super();
	}

	public Cell(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(x).append(y).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof Cell)) {
			return false;
		}
		Cell other = (Cell) obj;
		return new EqualsBuilder().append(x, other.x).append(y, other.y).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{").append(x).append(",").append(y).append("}");
		return builder.toString();
	}
}
