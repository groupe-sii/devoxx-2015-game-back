package fr.sii.survival.core.domain.action;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.image.Image;

public class AddImage implements Action {
	/**
	 * The image to add
	 */
	private Image image;

	/**
	 * The position where to place the image on the game board
	 */
	private Cell cell;

	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	public AddImage() {
		super();
	}

	public AddImage(Image image, Cell cell) {
		super();
		this.image = image;
		this.cell = cell;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
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
		result = prime * result + ((image == null) ? 0 : image.hashCode());
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
		AddImage other = (AddImage) obj;
		if (cell == null) {
			if (other.cell != null)
				return false;
		} else if (!cell.equals(other.cell))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddImage [image=").append(image).append(", cell=").append(cell).append("]");
		return builder.toString();
	}

}
