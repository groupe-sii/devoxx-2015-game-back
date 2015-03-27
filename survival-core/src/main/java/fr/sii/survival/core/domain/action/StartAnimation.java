package fr.sii.survival.core.domain.action;

import fr.sii.survival.core.domain.animation.AnimationOptions;

/**
 * Action that starts an animation
 * 
 * @author Aurélien Baudet
 *
 */
public class StartAnimation implements Action {
	/**
	 * The name of the animation to start
	 */
	private String name;

	/**
	 * The duration of the animation (override the duration provided by the
	 * animation itself)
	 */
	private Long duration;

	/**
	 * The options for the animation (override the options provided by the
	 * animation itself)
	 */
	private AnimationOptions options;

	public StartAnimation(String name) {
		this(name, null, null);
	}

	public StartAnimation(String name, AnimationOptions options) {
		this(name, null, options);
	}

	public StartAnimation(String name, Long duration) {
		this(name, duration, null);
	}

	public StartAnimation(String name, Long duration, AnimationOptions options) {
		super();
		this.name = name;
		this.duration = duration;
		this.options = options;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public AnimationOptions getOptions() {
		return options;
	}

	public void setOptions(AnimationOptions options) {
		this.options = options;
	}
}
