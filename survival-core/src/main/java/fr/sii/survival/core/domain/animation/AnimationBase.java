package fr.sii.survival.core.domain.animation;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Base class for animations. An animation must have a name and a duration. An
 * animation can also provide other information useful for running it like the
 * number of times to run it and in which direction.
 * 
 * @author aurelien
 *
 */
public abstract class AnimationBase implements Animation {
	private static long counter = 0;

	/**
	 * Unique id of the animation
	 */
	protected final String id;

	/**
	 * The name of the animation
	 */
	protected String name;

	/**
	 * The whole duration of the animation (in milliseconds)
	 */
	protected long duration;

	/**
	 * The animation options
	 */
	protected AnimationOptions options;

	/**
	 * The animation will be executed using default options:
	 * <ul>
	 * <li>starts immediately</li>
	 * <li>play in normal direction</li>
	 * <li>run only one time</li>
	 * </ul>
	 * 
	 * @param name
	 *            the name of the animation
	 * @param duration
	 *            the duration of the animation (in milliseconds)
	 */
	public AnimationBase(String name, long duration) {
		this(name, duration, new AnimationOptions());
	}

	/**
	 * The animation will be executed using provided options.
	 * 
	 * @param name
	 *            the name of the animation
	 * @param duration
	 *            the duration of the animation (in milliseconds)
	 * @param options
	 *            the animation options
	 */
	public AnimationBase(String name, long duration, AnimationOptions options) {
		super();
		this.id = "Animation-" + (counter++);
		this.name = name;
		this.duration = duration;
		this.options = options;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getDuration() {
		return duration;
	}

	public AnimationOptions getOptions() {
		return options;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof PropertiesAnimation)) {
			return false;
		}
		PropertiesAnimation other = (PropertiesAnimation) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnimationBase [id=").append(id).append(", name=").append(name).append(", duration=").append(duration).append(", options=").append("]");
		return builder.toString();
	}

}