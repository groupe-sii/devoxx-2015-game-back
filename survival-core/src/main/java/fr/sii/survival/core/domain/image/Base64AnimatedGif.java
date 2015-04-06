package fr.sii.survival.core.domain.image;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.util.sprite.Base64Writer;
import fr.sii.survival.core.util.sprite.SpriteWriter;

/**
 * Provide useful information for sprite. Can also generate a sprite if you
 * provide the images to combine into one. The image will be generated
 * horizontally. All images MUST have the same size.
 * 
 * The sprite image will be generated in memory and base64 encoded (see
 * {@link Base64ServerImage})
 * 
 * @author Aurélien Baudet
 *
 */
public class Base64AnimatedGif extends AnimatedGifBase {
	/**
	 * Generate a sprite image with all images contained in the provided folder
	 * (server side). The images are sorted by their name. All images MUST have
	 * the same size. The generated sprite image will be stored in memory and
	 * base64 encoded.
	 * 
	 * @param folder
	 *            the folder that contains images
	 * @throws IOException
	 *             when any of the image couldn't be read or the folder doesn't
	 *             exist
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public Base64AnimatedGif(String folder) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), folder);
	}

	/**
	 * Generate a sprite image with all images contained in the provided folder
	 * (server side). The images are sorted by their name. All images MUST have
	 * the same size. The generated sprite image will be stored in memory and
	 * base64 encoded.
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
	public Base64AnimatedGif(String folder, boolean reverse) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), folder, reverse);
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size. The generated sprite image will be stored in memory and
	 * base64 encoded.
	 * 
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public Base64AnimatedGif(InputStream... images) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), images);
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size. The generated sprite image will be stored in memory and
	 * base64 encoded.
	 * 
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public Base64AnimatedGif(List<InputStream> images) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), images);
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size. The generated sprite image will be stored in memory and
	 * base64 encoded.
	 * 
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public Base64AnimatedGif(UriImage... images) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), images);
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size. The generated sprite image will be stored in memory and
	 * base64 encoded.
	 * 
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public Base64AnimatedGif(Base64ServerImage... images) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), images);
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
	public Base64AnimatedGif(SpriteWriter writer, String folder) throws IOException, MimetypeDetectionException {
		super(writer, folder);
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
	public Base64AnimatedGif(SpriteWriter writer, String folder, boolean reverse) throws IOException, MimetypeDetectionException {
		super(writer, folder, reverse);
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
	public Base64AnimatedGif(SpriteWriter writer, InputStream... images) throws IOException, MimetypeDetectionException {
		super(writer, images);
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
	public Base64AnimatedGif(SpriteWriter writer, List<InputStream> images) throws IOException, MimetypeDetectionException {
		super(writer, images);
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
	public Base64AnimatedGif(SpriteWriter writer, UriImage... images) throws IOException, MimetypeDetectionException {
		super(writer, images);
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
	public Base64AnimatedGif(SpriteWriter writer, Base64ServerImage... images) throws IOException, MimetypeDetectionException {
		super(writer, images);
	}

	private static SpriteWriter getDefaultWriter() {
		return new Base64Writer();
	}
}
