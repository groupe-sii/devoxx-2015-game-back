package fr.sii.survival.core.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import fr.sii.survival.core.domain.image.Base64ServerImage;
import fr.sii.survival.core.domain.image.ServerImage;
import fr.sii.survival.core.domain.image.UriImage;
import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.util.sprite.ServerSprite;
import fr.sii.survival.core.util.sprite.SpriteImage;

public class SpriteUtil {
	public static final String SPRITE_GENERATION_FOLDER = "survival-sprites";

	private SpriteUtil() {
		super();
	}

	/**
	 * Shortcut for
	 * <code>SpriteUtil.toServerSprite(SpriteUtil.generate(ImageUtil.read(images)), true)</code>
	 * 
	 * The sprite image will be generated in a temporary directory and the
	 * {@link UriImage} will points to it.
	 * 
	 * @param images
	 *            the list of image streams
	 * @param store
	 *            store the sprite into temporary file
	 * @return the sprite image metadata
	 * @throws IOException
	 *             when any image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined
	 */
	public static ServerSprite fromUriImages(List<UriImage> images, boolean store) throws IOException, MimetypeDetectionException {
		return fromUriImages(images, 0, store);
	}

	/**
	 * Shortcut for
	 * <code>SpriteUtil.toServerSprite(SpriteUtil.generate(ImageUtil.readUriImages(images), margin), true)</code>
	 * 
	 * The sprite image will be generated in a temporary directory and the
	 * {@link UriImage} will points to it.
	 * 
	 * @param images
	 *            the list of image streams
	 * @param margin
	 *            the margin in pixel between each image
	 * @param store
	 *            store the sprite into temporary file
	 * @return the sprite image metadata
	 * @throws IOException
	 *             when any image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined
	 */
	public static ServerSprite fromUriImages(List<UriImage> images, int margin, boolean store) throws IOException, MimetypeDetectionException {
		return toServerSprite(generate(ImageUtil.readUriImages(images), margin), store);
	}

	/**
	 * Shortcut for
	 * <code>SpriteUtil.toServerSprite(SpriteUtil.generate(ImageUtil.read(images)), false)</code>
	 * 
	 * @param images
	 *            the list of image streams
	 * @param store
	 *            store the sprite into temporary file
	 * @return the sprite image metadata
	 * @param store
	 *            store the sprite into temporary file
	 * @throws IOException
	 *             when any image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined
	 */
	public static ServerSprite fromBase64Images(List<Base64ServerImage> images, boolean store) throws IOException, MimetypeDetectionException {
		return fromBase64Images(images, 0, store);
	}

	/**
	 * Shortcut for
	 * <code>SpriteUtil.toServerSprite(SpriteUtil.generate(ImageUtil.read(images), margin), false)</code>
	 * 
	 * @param images
	 *            the list of image streams
	 * @param margin
	 *            the margin in pixel between each image
	 * @param store
	 *            store the sprite into temporary file
	 * @return the sprite image metadata
	 * @throws IOException
	 *             when any image couldn't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype couldn't be determined
	 */
	public static ServerSprite fromBase64Images(List<Base64ServerImage> images, int margin, boolean store) throws IOException, MimetypeDetectionException {
		return toServerSprite(generate(ImageUtil.read(images), margin), store);
	}

	/**
	 * Shortcut for <code>SpriteUtil.generate(ImageUtil.load(images))</code>
	 * 
	 * @param images
	 *            the list of image streams
	 * @return the sprite image metadata
	 * @throws IOException
	 *             when any image couldn't be read
	 */
	public static SpriteImage fromStream(List<InputStream> images) throws IOException {
		return fromStream(images, 0);
	}

	/**
	 * Shortcut for
	 * <code>SpriteUtil.generate(ImageUtil.load(images), margin)</code>
	 * 
	 * @param images
	 *            the list of image streams
	 * @param margin
	 *            the margin in pixel between each image
	 * @return the sprite image metadata
	 * @throws IOException
	 *             when any image couldn't be read
	 */
	public static SpriteImage fromStream(List<InputStream> images, int margin) throws IOException {
		return generate(ImageUtil.load(images), margin);
	}

	/**
	 * Generate a sprite image from the provided list of images. The image is
	 * generated horizontally. A margin can be provided in order to generate
	 * space between each image.
	 * 
	 * @param images
	 *            the list of images to place in the sprite
	 * @param margin
	 *            the margin between each image
	 * @return the sprite
	 */
	public static SpriteImage generate(List<BufferedImage> images, int margin) {
		int totalWidth = 0;
		int maxHeight = 0;
		int type = 0;
		// compute width and height
		for (BufferedImage image : images) {
			type = image.getType();
			totalWidth += image.getWidth() + margin;
			if (image.getHeight() > maxHeight) {
				maxHeight = image.getHeight();
			}
		}
		// generate the sprite
		BufferedImage sprite = new BufferedImage(totalWidth, maxHeight, type);
		int currentX = 0;
		Graphics g = sprite.getGraphics();
		for (BufferedImage img : images) {
			g.drawImage(img, currentX, 0, null);
			currentX += img.getWidth() + margin;
		}
		return new SpriteImage(sprite, images);
	}

	/**
	 * Convert the sprite image into a logical representation of an image. If
	 * store is true, then the sprite is converted into a {@link UriImage}. If
	 * store is false, then the sprite is converted into a
	 * {@link Base64ServerImage}.
	 * 
	 * @param sprite
	 *            the sprite to convert
	 * @param store
	 *            store the sprite into temporary file
	 * @return the sprite that contains the server image
	 * @throws MimetypeDetectionException
	 *             when the mimetype of the sprite couldn't be determined
	 * @throws IOException
	 *             when the sprite couldn't be converted
	 */
	public static ServerSprite toServerSprite(SpriteImage sprite, boolean store) throws MimetypeDetectionException, IOException {
		ServerImage image;
		if (store) {
			File file = FileUtil.getRandomFile(SPRITE_GENERATION_FOLDER, "sprite", ".png");
			ImageIO.write(sprite.getSprite(), "png", file);
			image = new UriImage(file);
		} else {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImageIO.write(sprite.getSprite(), "png", output);
			image = new Base64ServerImage(output.toByteArray());
		}
		BufferedImage frame = sprite.getFrames().get(0);
		Dimension imageSize = new Dimension(sprite.getSprite().getWidth(), sprite.getSprite().getHeight());
		Dimension frameSize = new Dimension(frame.getWidth(), frame.getHeight());
		return new ServerSprite(image, imageSize, frameSize);
	}
}
