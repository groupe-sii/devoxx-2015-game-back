package fr.sii.survival.core.domain.image;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * References an image or an animation that was previously generated. Basically,
 * an animation will provide a CSS class. When this class is applied, the
 * animation is played. This kind of image is used to apply the CSS class on the
 * cell or player to play the animation.
 * 
 * @author Aurélien Baudet
 *
 */
public class ImageReference implements ClientHostedImage {
	private static long counter = 0;

	/**
	 * The unique id of the image
	 */
	private String id;
	
	/**
	 * The name of the image to reference
	 */
	private final String name;

	public ImageReference(String name) {
		super();
		this.id = "ImageReference-"+(counter++);
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if(obj instanceof ImageReference) {
			return false;
		} else {
			ImageReference other = (ImageReference) obj;
			return new EqualsBuilder().append(id, other.id).isEquals();
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImageReference [name=").append(name).append("]");
		return builder.toString();
	}
}
