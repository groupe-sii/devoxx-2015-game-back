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
 * The animation can automatically generates linear percentage for frames. But
 * if steps are provided, the animation is no more linear and let you provide
 * manually the steps.
 * 
 * @author aurelien
 *
 */
public class SpriteAnimation extends PropertiesAnimation {
	/**
	 * The sprite image
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
	}

	/**
	 * Generates a manual repartition for the animation based on provided steps.
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
	public SpriteAnimation(String name, long duration, Sprite sprite, AnimationOptions options, float... steps) {
		super(name, duration, options, toFrames(sprite, steps));
	}

	/**
	 * Generates a manual repartition for the animation based on provided steps.
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
	public SpriteAnimation(String name, long duration, Sprite sprite, float... steps) {
		super(name, duration, toFrames(sprite, steps));
	}

	public Sprite getSprite() {
		return sprite;
	}

	private static List<Frame> toFrames(Sprite sprite, float[] steps) {
		int numImages = sprite.getWidth() / sprite.getFrameWidth();
		List<Frame> frames = new ArrayList<Frame>(numImages);
		for (int i = 0; i < numImages; i++) {
			int x = i * sprite.getFrameWidth();
			float percentage = steps == null ? (float) i * 100 / (float) numImages : steps[i];
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
