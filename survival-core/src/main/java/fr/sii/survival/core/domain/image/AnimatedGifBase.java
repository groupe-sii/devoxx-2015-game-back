package fr.sii.survival.core.domain.image;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.util.ImageUtil;
import fr.sii.survival.core.util.gif.GifUtil;
import fr.sii.survival.core.util.gif.GifWriter;

/**
 * Provide useful information for animated gif. Can also generate an animated
 * gif if you provide the images to combine into one. All images MUST have the
 * same size.
 * 
 * @author Aurélien Baudet
 *
 */
public class AnimatedGifBase implements AnimatedGif {
	private static long counter = 0;

	/**
	 * Unique id of the image
	 */
	private final String id;

	/**
	 * The sprite image
	 */
	private ServerImage image;

	/**
	 * Generate a sprite image with all images contained in the provided folder
	 * (server side). The images are sorted by their name. All images MUST have
	 * the same size. Use the provided sprite writing strategy
	 * 
	 * @param writer
	 *            the sprite generation writing strategy
	 * @param folder
	 *            the folder that contains images
	 * 
	 * @throws IOException
	 *             when any of the image couldn't be read or the folder doesn't
	 *             exist
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public AnimatedGifBase(GifWriter writer, String folder, long delayBetweenFrames, int loops) throws IOException, MimetypeDetectionException {
		this(writer, folder, false, delayBetweenFrames, loops);
	}

	/**
	 * Generate a sprite image with all images contained in the provided folder
	 * (server side). The images are sorted by their name. All images MUST have
	 * the same size. Use the provided sprite writing strategy
	 * 
	 * @param writer
	 *            the sprite generation writing strategy
	 * @param folder
	 *            the folder that contains images
	 * @param reverse
	 *            walk images in reverse order
	 * @throws IOException
	 *             when any of the image couldn't be read or the folder doesn't
	 *             exist
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public AnimatedGifBase(GifWriter writer, String folder, boolean reverse, long delayBetweenFrames, int loops) throws IOException, MimetypeDetectionException {
		this(writer, delayBetweenFrames, loops, ImageUtil.loadStreams(folder, reverse));
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size. Use the provided sprite writing strategy
	 * 
	 * @param writer
	 *            the sprite generation writing strategy
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public AnimatedGifBase(GifWriter writer, long delayBetweenFrames, int loops, InputStream... images) throws IOException, MimetypeDetectionException {
		this(writer, delayBetweenFrames, loops, Arrays.asList(images));
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size. Use the provided sprite writing strategy
	 * 
	 * @param writer
	 *            the sprite generation writing strategy
	 * @param images
	 *            the list of images that will compose the sprite
	 * 
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public AnimatedGifBase(GifWriter writer, long delayBetweenFrames, int loops, List<InputStream> images) throws IOException, MimetypeDetectionException {
		this(GifUtil.generate(ImageUtil.load(images), delayBetweenFrames, loops, writer));
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size. Use the provided sprite writing strategy
	 * 
	 * @param writer
	 *            the sprite generation writing strategy
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public AnimatedGifBase(GifWriter writer, long delayBetweenFrames, int loops, UriImage... images) throws IOException, MimetypeDetectionException {
		this(GifUtil.fromUriImages(Arrays.asList(images), delayBetweenFrames, loops, writer));
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size. Use the provided sprite writing strategy
	 * 
	 * @param writer
	 *            the sprite generation writing strategy
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public AnimatedGifBase(GifWriter writer, long delayBetweenFrames, int loops, Base64ServerImage... images) throws IOException, MimetypeDetectionException {
		this(GifUtil.fromBase64Images(Arrays.asList(images), delayBetweenFrames, loops, writer));
	}

	protected AnimatedGifBase(ServerImage image) {
		super();
		this.id = "AnimatedGifBase-" + (counter++);
		this.image = image;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.sii.survival.core.domain.image.Sprite#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.sii.survival.core.domain.image.Sprite#getImage()
	 */
	@Override
	public ServerImage getImage() {
		return image;
	}

}
