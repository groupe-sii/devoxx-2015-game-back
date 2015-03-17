package fr.sii.survival.core.domain.action;

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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
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
		MoveImage other = (MoveImage) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
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
		builder.append("MoveImage [image=").append(image).append(", start=").append(start).append(", end=").append(end).append("]");
		return builder.toString();
	}
}
