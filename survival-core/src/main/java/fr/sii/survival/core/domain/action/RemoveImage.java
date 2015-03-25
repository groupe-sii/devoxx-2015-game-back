package fr.sii.survival.core.domain.action;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.image.Image;

public class RemoveImage implements Action {
	/**
	 * The image to remove
	 */
	private Image image;

	/**
	 * The position of the image on the game board
	 */
	private Cell cell;

	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	@Deprecated
	public RemoveImage() {
		super();
	}

	public RemoveImage(Image image, Cell cell) {
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
		return new HashCodeBuilder().append(cell).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof RemoveImage)) {
			return false;
		}
		RemoveImage other = (RemoveImage) obj;
		return new EqualsBuilder().append(cell, other.cell).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RemoveImage [cell=").append(cell).append("]");
		return builder.toString();
	}

}
