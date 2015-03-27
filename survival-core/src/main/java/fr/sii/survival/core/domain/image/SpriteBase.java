package fr.sii.survival.core.domain.image;

import java.awt.Dimension;
import java.io.IOException;

import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.util.sprite.ServerSprite;

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
