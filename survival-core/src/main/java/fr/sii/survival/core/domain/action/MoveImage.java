package fr.sii.survival.core.domain.action;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.image.Image;

public class MoveImage implements Action {
	/**
	 * The image to move
	 */
	private Image image;

	/**
	 * The start position of the image on the game board
	 */
	private Cell start;

	/**
	 * The end position of the image on the game board
	 */
	private Cell end;

	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	public MoveImage() {
		super();
	}

	public MoveImage(Image image, Cell start, Cell end) {
		super();
		this.image = image;
		this.start = start;
		this.end = end;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
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
		return new HashCodeBuilder().append(start).append(end).append(image).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof MoveImage)) {
			return false;
		}
		MoveImage other = (MoveImage) obj;
		return new EqualsBuilder().append(start, other.start).append(end, other.end).append(image, other.image).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MoveImage [image=").append(image).append(", start=").append(start).append(", end=").append(end).append("]");
		return builder.toString();
	}
}
