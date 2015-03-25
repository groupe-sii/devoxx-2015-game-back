package fr.sii.survival.core.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import fr.sii.survival.core.domain.image.Base64ServerImage;
import fr.sii.survival.core.domain.image.UriImage;
import fr.sii.survival.core.exception.MimetypeDetectionException;

public class ImageUtil {
	private ImageUtil() {
		super();
	}
	
	/**
	 * Load all images available in the provided folder
	 * 
	 * @param folder
	 *            the folder that contains images
	 * @param reverse
	 *            walk into the folder in reverse order if true
	 * @return the list of found images
	 * @throws IOException
	 *             when an image couldn't be found
	 * @throws MimetypeDetectionException
	 *             when the mimetype of an image couldn't be found
	 */
	public static List<Base64ServerImage> load(String folder, boolean reverse) throws IOException, MimetypeDetectionException {
		List<InputStream> streams = loadStreams(folder, reverse);
		List<Base64ServerImage> images = new ArrayList<>(streams.size());
		for (InputStream stream : streams) {
			images.add(new Base64ServerImage(stream));
		}
		return images;
	}

	/**
	 * Load all images available in the provided folder
	 * 
	 * @param folder
	 *            the folder that contains images
	 * @param reverse
	 *            walk into the folder in reverse order if true
	 * @return the list of stream images
	 * @throws IOException
	 *             when an image couldn't be found
	 * @throws MimetypeDetectionException
	 *             when the mimetype of an image couldn't be found
	 */
	public static List<InputStream> loadStreams(String folder, boolean reverse) throws IOException, MimetypeDetectionException {
		List<InputStream> images = ScanUtil.scan(folder, reverse, file -> Base64ServerImage.class.getResourceAsStream("/" + file));
		if(images.isEmpty()) {
			throw new IOException("Folder "+folder+" doesn't contain any image");
		}
		return images;
	}

	/**
	 * Load a list of images from the provided streams
	 * 
	 * @param images
	 *            the image streams
	 * @return the list of loaded images
	 * @throws IOException
	 *             when any image couldn't be read
	 */
	public static List<BufferedImage> load(List<InputStream> images) throws IOException {
		// walk through images to compute sprite size and getting list of images
		int i = 0;
		List<BufferedImage> imgs = new ArrayList<BufferedImage>(images.size());
		for (InputStream image : images) {
			BufferedImage img = ImageIO.read(image);
			if (img == null) {
				throw new IOException("Failed to load image " + i);
			}
			imgs.add(img);
		}
		return imgs;
	}

	/**
	 * Read content of a server image and convert it to Java image
	 * 
	 * @param image
	 *            the image to read
	 * @return the buffered image
	 * @throws IOException
	 *             when image couldn't be read
	 */
	public static BufferedImage read(UriImage image) throws IOException {
		BufferedImage img = ImageIO.read(image.getUri().toURL());
		if (img == null) {
			throw new IOException("Failed to read server image");
		}
		return img;
	}

	/**
	 * Read a list of server images and return the content of each image
	 * 
	 * @param images
	 *            the list of images to read
	 * @return the list of images
	 * @throws IOException
	 *             when any of the image couldn't be read
	 */
	public static List<BufferedImage> readUriImages(List<UriImage> images) throws IOException {
		List<BufferedImage> imgs = new ArrayList<>(images.size());
		for (UriImage img : images) {
			imgs.add(read(img));
		}
		return imgs;
	}
	
	/**
	 * Read content of a server image and convert it to Java image
	 * 
	 * @param image
	 *            the image to read
	 * @return the buffered image
	 * @throws IOException
	 *             when image couldn't be read
	 */
	public static BufferedImage read(Base64ServerImage image) throws IOException {
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(image.getContent())));
		if (img == null) {
			throw new IOException("Failed to read server image");
		}
		return img;
	}

	/**
	 * Read a list of server images and return the content of each image
	 * 
	 * @param images
	 *            the list of images to read
	 * @return the list of images
	 * @throws IOException
	 *             when any of the image couldn't be read
	 */
	public static List<BufferedImage> read(List<Base64ServerImage> images) throws IOException {
		List<BufferedImage> imgs = new ArrayList<>(images.size());
		for (Base64ServerImage img : images) {
			imgs.add(read(img));
		}
		return imgs;
	}
}
