package fr.sii.survival.core.domain.image;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.util.ImageUtil;
import fr.sii.survival.core.util.SpriteUtil;
import fr.sii.survival.core.util.sprite.ServerSprite;
import fr.sii.survival.core.util.sprite.SpriteWriter;

/**
 * Provide useful information for sprite. Can also generate a sprite if you
 * provide the images to combine into one. The image will be generated
 * horizontally. All images MUST have the same size.
 * 
 * @author Aurélien Baudet
 *
 */
public class SpriteBase implements Sprite {
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
	 * The width of sprite image
	 */
	private int width;

	/**
	 * The height of sprite image
	 */
	private int height;

	/**
	 * The width of one image contained in the sprite
	 */
	private int frameWidth;

	/**
	 * The height of one image contained in the sprite
	 */
	private int frameHeight;

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
	public SpriteBase(SpriteWriter writer, String folder) throws IOException, MimetypeDetectionException {
		this(writer, folder, false);
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
	public SpriteBase(SpriteWriter writer, String folder, boolean reverse) throws IOException, MimetypeDetectionException {
		this(writer, ImageUtil.loadStreams(folder, reverse));
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
	public SpriteBase(SpriteWriter writer, InputStream... images) throws IOException, MimetypeDetectionException {
		this(writer, Arrays.asList(images));
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
	public SpriteBase(SpriteWriter writer, List<InputStream> images) throws IOException, MimetypeDetectionException {
		this(SpriteUtil.toServerSprite(SpriteUtil.fromStream(images), writer));
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
	public SpriteBase(SpriteWriter writer, UriImage... images) throws IOException, MimetypeDetectionException {
		this(SpriteUtil.fromUriImages(Arrays.asList(images), writer));
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
	public SpriteBase(SpriteWriter writer, Base64ServerImage... images) throws IOException, MimetypeDetectionException {
		this(SpriteUtil.fromBase64Images(Arrays.asList(images), writer));
	}

	protected SpriteBase(ServerSprite serverSprite) throws IOException, MimetypeDetectionException {
		this(serverSprite.getImage(), serverSprite.getImageSize(), serverSprite.getFrameSize());
	}

	protected SpriteBase(ServerImage image, Dimension imageSize, Dimension frameSize) {
		this(image, imageSize.width, imageSize.height, frameSize.width, frameSize.height);
	}

	protected SpriteBase(ServerImage image, int width, int height, int frameWidth, int frameHeight) {
		super();
		this.id = "SpriteBase-" + (counter++);
		this.image = image;
		this.width = width;
		this.height = height;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.sii.survival.core.domain.image.Sprite#getWidth()
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.sii.survival.core.domain.image.Sprite#getHeight()
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.sii.survival.core.domain.image.Sprite#getFrameWidth()
	 */
	@Override
	public int getFrameWidth() {
		return frameWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.sii.survival.core.domain.image.Sprite#getFrameHeight()
	 */
	@Override
	public int getFrameHeight() {
		return frameHeight;
	}
}
