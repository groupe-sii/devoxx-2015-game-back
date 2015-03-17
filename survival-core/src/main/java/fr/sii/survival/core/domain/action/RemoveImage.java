package fr.sii.survival.core.domain.action;

import fr.sii.survival.core.domain.board.Cell;

public class RemoveImage implements Action {
	/**
	 * The name of the image to remove
	 */
	private String name;

	/**
	 * The position of the image on the game board
	 */
	private Cell cell;

	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	public RemoveImage() {
		super();
	}

	public RemoveImage(String name, Cell cell) {
		super();
		this.name = name;
		this.cell = cell;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		RemoveImage other = (RemoveImage) obj;
		if (cell == null) {
			if (other.cell != null)
				return false;
		} else if (!cell.equals(other.cell))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RemoveImage [name=").append(name).append(", cell=").append(cell).append("]");
		return builder.toString();
	}
}
