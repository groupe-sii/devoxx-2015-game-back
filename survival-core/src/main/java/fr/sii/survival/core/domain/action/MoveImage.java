package fr.sii.survival.core.domain.action;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.image.Image;

public class MoveImage extends MoveActionBase {
	/**
	 * The image to move
	 */
	private Image image;

	public MoveImage(Image image, Cell start, Cell end) {
		super(start, end);
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().appendSuper(super.hashCode()).append(image).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof MoveImage)) {
			return false;
		}
		MoveImage other = (MoveImage) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(image, other.image).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MoveImage [image=").append(image).append(", ").append(super.toString()).append("]");
		return builder.toString();
	}
}
