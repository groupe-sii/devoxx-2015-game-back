package fr.sii.survival.core.domain.image;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.util.ImageUtil;
import fr.sii.survival.core.util.SpriteUtil;

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
public class Base64Sprite extends SpriteBase {

	/**
	 * Generate a sprite image with all images contained in the provided folder
	 * (server side). The images are sorted by their name. All images MUST have
	 * the same size.
	 * 
	 * @param folder
	 *            the folder that contains images
	 * @throws IOException
	 *             when any of the image couldn't be read or the folder doesn't
	 *             exist
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public Base64Sprite(String folder) throws IOException, MimetypeDetectionException {
		this(folder, false);
	}

	/**
	 * Generate a sprite image with all images contained in the provided folder
	 * (server side). The images are sorted by their name. All images MUST have
	 * the same size.
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
	public Base64Sprite(String folder, boolean reverse) throws IOException, MimetypeDetectionException {
		this(ImageUtil.loadStreams(folder, reverse));
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size.
	 * 
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public Base64Sprite(InputStream... images) throws IOException, MimetypeDetectionException {
		this(Arrays.asList(images));
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size.
	 * 
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public Base64Sprite(List<InputStream> images) throws IOException, MimetypeDetectionException {
		super(SpriteUtil.toServerSprite(SpriteUtil.fromStream(images), false));
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size.
	 * 
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public Base64Sprite(UriImage... images) throws IOException, MimetypeDetectionException {
		super(SpriteUtil.fromUriImages(Arrays.asList(images), false));
	}

	/**
	 * Generate a sprite image with all provided images. All images MUST have
	 * the same size.
	 * 
	 * @param images
	 *            the list of images that will compose the sprite
	 * @throws IOException
	 *             when any of the image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined for any of the images
	 */
	public Base64Sprite(Base64ServerImage... images) throws IOException, MimetypeDetectionException {
		super(SpriteUtil.fromBase64Images(Arrays.asList(images), false));
	}

}
