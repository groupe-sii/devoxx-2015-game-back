package fr.sii.survival.core.domain.animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.sii.survival.core.domain.image.Sprite;

/**
 * Generates an animation based on the provided sprite image. Each frame
 * represents one image of the sprite.
 * 
 * The animation automatically generates linear percentage for frames.
 * 
 * If steps are provided, the animation is no more linear and let you provide
 * manually the steps. Unfortunately, CSS3 animations doesn't support manual
 * steps so this feature is hidden.
 * 
 * @author Aurélien Baudet
 *
 */
public class SpriteAnimation extends PropertiesAnimation {
	private static final int PERCENT = 100;
	
	/**
	 * The sprite image to animate
	 */
	private Sprite sprite;

	/**
	 * Generates a linear repartition for the animation.
	 * 
	 * @param name
	 *            the name of the animation
	 * @param duration
	 *            the whole duration of the animation (in milliseconds)
	 * @param sprite
	 *            the sprite image that contains several images to animate
	 * @param options
	 *            the animation options
	 */
	public SpriteAnimation(String name, long duration, Sprite sprite, AnimationOptions options) {
		super(name, duration, options, toFrames(sprite, null));
		this.sprite = sprite;
	}

	/**
	 * Generates a linear repartition for the animation.
	 * 
	 * @param name
	 *            the name of the animation
	 * @param duration
	 *            the whole duration of the animation
	 * @param sprite
	 *            the sprite image that contains several images to animate
	 */
	public SpriteAnimation(String name, long duration, Sprite sprite) {
		super(name, duration, toFrames(sprite, null));
		this.sprite = sprite;
	}

	/**
	 * Generates a manual repartition for the animation based on provided steps.
	 * This constructor is now private because the steps doesn't work on client
	 * side with CSS animations
	 * 
	 * @param name
	 *            the name of the animation
	 * @param duration
	 *            the whole duration of the animation (in milliseconds)
	 * @param sprite
	 *            the sprite image that contains several images to animate
	 * @param options
	 *            the animation options
	 * @param steps
	 *            the manual percentage steps
	 */
	private SpriteAnimation(String name, long duration, Sprite sprite, AnimationOptions options, float... steps) {
		super(name, duration, options, toFrames(sprite, steps));
		this.sprite = sprite;
	}

	/**
	 * Generates a manual repartition for the animation based on provided steps.
	 * This constructor is now private because the steps doesn't work on client
	 * side with CSS animations
	 * 
	 * @param name
	 *            the name of the animation
	 * @param duration
	 *            the whole duration of the animation (in milliseconds)
	 * @param sprite
	 *            the sprite image that contains several images to animate
	 * @param steps
	 *            the manual percentage steps
	 */
	private SpriteAnimation(String name, long duration, Sprite sprite, float... steps) {
		super(name, duration, toFrames(sprite, steps));
		this.sprite = sprite;
	}

	public Sprite getSprite() {
		return sprite;
	}

	private static List<Frame> toFrames(Sprite sprite, float[] steps) {
		int numImages = sprite.getWidth() / sprite.getFrameWidth();
		List<Frame> frames = new ArrayList<Frame>(numImages);
		for (int i = 0; i < numImages; i++) {
			int x = i * sprite.getFrameWidth();
			float percentage = steps == null ? (float) i * PERCENT / (float) numImages : steps[i];
			frames.add(new Frame(percentage, toProps(x)));
		}
		return frames;
	}

	private static Map<String, Object> toProps(int x) {
		Map<String, Object> props = new HashMap<>();
		props.put("x", x);
		props.put("y", 0);
		return props;
	}
}
