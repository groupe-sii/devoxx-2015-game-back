package fr.sii.survival.core.domain.animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Declare an animation composed with provided frames. These frames are useful
 * for indicating to the client how to run it.
 * 
 * @author Aurélien Baudet
 *
 */
public class PropertiesAnimation extends AnimationBase {
	/**
	 * The list of frames that compose the animation
	 */
	private List<Frame> frames;

	public PropertiesAnimation(String name, long duration, Frame... frames) {
		this(name, duration, new ArrayList<>(Arrays.asList(frames)));
	}

	public PropertiesAnimation(String name, long duration, List<Frame> frames) {
		super(name, duration);
		this.frames = frames;
	}

	public PropertiesAnimation(String name, long duration, AnimationOptions options, Frame... frames) {
		this(name, duration, options, new ArrayList<>(Arrays.asList(frames)));
	}

	public PropertiesAnimation(String name, long duration, AnimationOptions options, List<Frame> frames) {
		super(name, duration, options);
		this.frames = frames;
	}

	public void addFrame(Frame frame) {
		frames.add(frame);
	}
	
	public void addFrame(float percentage, Map<String, Object> properties) {
		addFrame(new Frame(percentage, properties));
	}
	
	public List<Frame> getFrames() {
		return frames;
	}
}
