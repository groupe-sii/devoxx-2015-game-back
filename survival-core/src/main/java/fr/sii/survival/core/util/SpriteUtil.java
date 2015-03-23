package fr.sii.survival.core.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import fr.sii.survival.core.domain.image.ServerImage;
import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.util.sprite.ServerSprite;
import fr.sii.survival.core.util.sprite.SpriteImage;

public class SpriteUtil {
	/**
	 * Shortcut for
	 * <code>SpriteUtil.toServerSprite(SpriteUtil.generate(ImageUtil.read(images)))</code>
	 * 
	 * @param images
	 *            the list of image streams
	 * @return the sprite image
	 * @throws IOException
	 *             when any image couldn't be read
	 */
	public static ServerSprite fromServerImages(List<ServerImage> images) throws IOException, MimetypeDetectionException {
		return fromServerImages(images, 0);
	}

	/**
	 * Shortcut for
	 * <code>SpriteUtil.toServerSprite(SpriteUtil.generate(ImageUtil.read(images), margin))</code>
	 * 
	 * @param images
	 *            the list of image streams
	 * @param margin
	 *            the margin in pixel between each image
	 * @return the sprite image
	 * @throws IOException
	 *             when any image couldn't be read
	 */
	public static ServerSprite fromServerImages(List<ServerImage> images, int margin) throws IOException, MimetypeDetectionException {
		return toServerSprite(generate(ImageUtil.read(images), margin));
	}

	/**
	 * Shortcut for <code>SpriteUtil.generate(ImageUtil.load(images))</code>
	 * 
	 * @param images
	 *            the list of image streams
	 * @return the sprite image
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
	 * @return the sprite image
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
	 * Convert a sprite into a {@link ServerImage} that will be used in events.
	 * 
	 * @param sprite
	 *            the sprite to convert
	 * @return the sprite that contains the server image
	 * @throws MimetypeDetectionException
	 *             when the mimetype of the sprite couldn't be determined
	 * @throws IOException
	 *             when the sprite couldn't be converted
	 */
	public static ServerSprite toServerSprite(SpriteImage sprite) throws MimetypeDetectionException, IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(sprite.getSprite(), "png", output);
		return new ServerSprite(new ServerImage(output.toByteArray()), new Dimension(sprite.getSprite().getWidth(), sprite.getSprite().getHeight()));
	}
}
