package fr.sii.survival.core.domain.image;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.util.ImageUtil;

public class Animation implements Image {
	private static long counter = 0;
	
	/**
	 * Unique id of the image
	 */
	private final String id;
	
	/**
	 * The name of the animation
	 */
	private String name;

	/**
	 * The delay (in milliseconds) to wait before displaying the next image of the animation
	 */
	private int rate;

	/**
	 * The list of images to animate
	 */
	private List<? extends Image> images;

	/**
	 * The number of times that the animation should be run:
	 * <ul>
	 * <li>0 means no repeat</li>
	 * <li>1 and over means the exact number of repeat</li>
	 * <li>-1 means repeat forever (animation never stops)</li>
	 * </ul>
	 */
	private int repeat;

	/**
	 * Initialize an animation with server images by directly reading folder content in normal order
	 * 
	 * @param folder
	 *            the the folder that contains the images to put into the animation. It is also used for the animation name
	 * @param rate
	 *            the animation rate
	 * @throws MimetypeDetectionException 
	 * @throws IOException 
	 */
	public Animation(String folder, int rate) throws IOException, MimetypeDetectionException {
		this(folder, rate, folder);
	}

	/**
	 * Initialize an animation with server images by directly reading folder content
	 * 
	 * @param folder
	 *            the the folder that contains the images to put into the animation. It is also used for the animation name
	 * @param rate
	 *            the animation rate
	 *            @param reverse reverse the order of the animation (starts the animation by the end)
	 * @throws MimetypeDetectionException 
	 * @throws IOException 
	 */
	public Animation(String folder, int rate, boolean reverse) throws IOException, MimetypeDetectionException {
		this(folder, rate, folder, reverse);
	}

	/**
	 * Initialize an animation with server images by directly reading folder content in normal order
	 * 
	 * @param name
	 *            the name of the animation
	 * @param rate
	 *            the animation rate
	 * @param folder
	 *            the the folder that contains the images to put into the animation
	 * @throws MimetypeDetectionException 
	 * @throws IOException 
	 */
	public Animation(String name, int rate, String folder) throws IOException, MimetypeDetectionException {
		this(name, rate, ImageUtil.load(folder, false));
	}

	/**
	 * Initialize an animation with server images by directly reading folder content
	 * 
	 * @param name
	 *            the name of the animation
	 * @param rate
	 *            the animation rate
	 * @param folder
	 *            the the folder that contains the images to put into the animation
	 *            @param reverse reverse the order of the animation (starts the animation by the end)
	 * @throws MimetypeDetectionException 
	 * @throws IOException 
	 */
	public Animation(String name, int rate, String folder, boolean reverse) throws IOException, MimetypeDetectionException {
		this(name, rate, ImageUtil.load(folder, reverse));
	}

	/**
	 * Initialize an animation without repeat
	 * 
	 * @param name
	 *            the name of the animation
	 * @param rate
	 *            the animation rate
	 * @param images
	 *            the images that compose the animation
	 */
	public Animation(String name, int rate, Image... images) {
		this(name, rate, -1, images);
	}

	/**
	 * Initialize an animation without repeat
	 * 
	 * @param name
	 *            the name of the animation
	 * @param rate
	 *            the animation rate
	 * @param images
	 *            the images that compose the animation
	 */
	public Animation(String name, int rate, List<? extends Image> images) {
		this(name, rate, -1, images);
	}

	public Animation(String name, int rate, int repeat, Image... images) {
		this(name, rate, repeat, Arrays.asList(images));
	}

	public Animation(String name, int rate, int repeat, List<? extends Image> images) {
		super();
		this.id = "Animation-"+(counter++);
		this.name = name;
		this.rate = rate;
		this.images = images;
		this.repeat = repeat;
	}

	public String getId() {
		return id;
	}

	public int getRate() {
		return rate;
	}

	public List<? extends Image> getImages() {
		return images;
	}

	public String getName() {
		return name;
	}

	public int getRepeat() {
		return repeat;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof Animation)) {
			return false;
		}
		Animation other = (Animation) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Animation [id=").append(id).append(", name=").append(name).append(", rate=").append(rate).append("]");
		return builder.toString();
	}

}
