package fr.sii.survival.core.domain.image;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.util.sprite.SpriteWriter;
import fr.sii.survival.core.util.sprite.TemporaryFileWriter;

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
public class UriSprite extends SpriteBase {
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
	public UriSprite(String folder) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), folder);
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
	public UriSprite(String folder, boolean reverse) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), folder, reverse);
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
	public UriSprite(InputStream... images) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), images);
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
	public UriSprite(List<InputStream> images) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), images);
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
	public UriSprite(UriImage... images) throws IOException, MimetypeDetectionException {
		this(getDefaultWriter(), images);
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
	public UriSprite(Base64ServerImage... images) throws IOException, MimetypeDetectionException {
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
	public UriSprite(SpriteWriter writer, String folder) throws IOException, MimetypeDetectionException {
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
	public UriSprite(SpriteWriter writer, String folder, boolean reverse) throws IOException, MimetypeDetectionException {
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
	public UriSprite(SpriteWriter writer, InputStream... images) throws IOException, MimetypeDetectionException {
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
	public UriSprite(SpriteWriter writer, List<InputStream> images) throws IOException, MimetypeDetectionException {
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
	public UriSprite(SpriteWriter writer, UriImage... images) throws IOException, MimetypeDetectionException {
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
	public UriSprite(SpriteWriter writer, Base64ServerImage... images) throws IOException, MimetypeDetectionException {
		super(writer, images);
	}

	private static SpriteWriter getDefaultWriter() {
		return new TemporaryFileWriter();
	}
}
