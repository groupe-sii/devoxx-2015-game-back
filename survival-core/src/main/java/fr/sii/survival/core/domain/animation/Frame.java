package fr.sii.survival.core.domain.animation;

import java.util.Map;

/**
 * Define a single step in the animation.
 * 
 * @author Aurélien Baudet
 *
 */
public class Frame {
	/**
	 * A percentage of the time through the animation sequence at which the
	 * frame should occur
	 */
	private float percentage;

	/**
	 * THe list of properties to animate
	 */
	private Map<String, Object> properties;

	public Frame(float percentage, Map<String, Object> properties) {
		super();
		this.percentage = percentage;
		this.properties = properties;
	}

	public float getPercentage() {
		return percentage;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}
}
