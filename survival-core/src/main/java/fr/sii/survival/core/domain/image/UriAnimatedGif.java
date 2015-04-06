package fr.sii.survival.core.domain.image;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.util.gif.GifWriter;
import fr.sii.survival.core.util.gif.TemporaryFileWriter;

/**
 * Provide useful information for sprite. Can also generate a sprite if you
 * provide the images to combine into one. The image will be generated
 * horizontally. All images MUST have the same size.
 * 
 * The sprite image will be generated into a temporary folder. The generated
 * sprite will then be accessible through an URL (see {@link UriImage})
 * 
 * @author Aurélien Baudet
 *
 */
public class UriAnimatedGif extends AnimatedGifBase {
	/**
	 * Generate a sprite image with all images contained in the provided folder
	 * (server side). The images are sorted by their name. All images MUST have
	 * the same size. The generated sprite will be stored as a file into a
	 * temporary directory
	 * 
	 * @param folder
	 *            the folder that contains images
	 * @throws IOException
	 *             when any of the image couldn't be read or the folder doesn't
	 *             exist
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public UriAnimatedGif(String folder, long delayBetweenFrames, int loops) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), folder, false, delayBetweenFrames, loops);
	}

	/**
	 * Generate a sprite image with all images contained in the provided folder
	 * (server side). The images are sorted by their name. All images MUST have
	 * the same size. The generated sprite will be stored as a file into a
	 * temporary directory
	 * 
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
	public UriAnimatedGif(String folder, boolean reverse, long delayBetweenFrames, int loops) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), folder, reverse, delayBetweenFrames, loops);
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size. The generated sprite will be stored as a file into a
	 * temporary directory
	 * 
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public UriAnimatedGif(long delayBetweenFrames, int loops, InputStream... images) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), delayBetweenFrames, loops, images);
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size. The generated sprite will be stored as a file into a
	 * temporary directory
	 * 
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public UriAnimatedGif(long delayBetweenFrames, int loops, List<InputStream> images) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), delayBetweenFrames, loops, images);
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size. The generated sprite will be stored as a file into a
	 * temporary directory
	 * 
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public UriAnimatedGif(long delayBetweenFrames, int loops, UriImage... images) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), delayBetweenFrames, loops, images);
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size. The generated sprite will be stored as a file into a
	 * temporary directory
	 * 
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public UriAnimatedGif(long delayBetweenFrames, int loops, Base64ServerImage... images) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), delayBetweenFrames, loops, images);
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
	 * 
	 * @throws IOException
	 *             when any of the image couldn't be read or the folder doesn't
	 *             exist
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public UriAnimatedGif(GifWriter writer, String folder, long delayBetweenFrames, int loops) throws IOException, MimetypeDetectionException {
		super(writer, folder, delayBetweenFrames, loops);
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
	 * 
	 * @throws IOException
	 *             when any of the image couldn't be read or the folder doesn't
	 *             exist
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public UriAnimatedGif(GifWriter writer, String folder, boolean reverse, long delayBetweenFrames, int loops) throws IOException, MimetypeDetectionException {
		super(writer, folder, reverse, delayBetweenFrames, loops);
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
	public UriAnimatedGif(GifWriter writer, long delayBetweenFrames, int loops, InputStream... images) throws IOException, MimetypeDetectionException {
		super(writer, delayBetweenFrames, loops, images);
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
	public UriAnimatedGif(GifWriter writer, long delayBetweenFrames, int loops, List<InputStream> images) throws IOException, MimetypeDetectionException {
		super(writer, delayBetweenFrames, loops, images);
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
	public UriAnimatedGif(GifWriter writer, long delayBetweenFrames, int loops, UriImage... images) throws IOException, MimetypeDetectionException {
		super(writer, delayBetweenFrames, loops, images);
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
	public UriAnimatedGif(GifWriter writer, long delayBetweenFrames, int loops, Base64ServerImage... images) throws IOException, MimetypeDetectionException {
		super(writer, delayBetweenFrames, loops, images);
	}

	private static GifWriter getDefaultWriter() {
		return new TemporaryFileWriter();
	}
}
