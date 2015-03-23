package fr.sii.survival.core.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.util.ClasspathHelper;

import fr.sii.survival.core.domain.image.ServerImage;
import fr.sii.survival.core.exception.MimetypeDetectionException;

public class ImageUtil {
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
	public static List<ServerImage> load(String folder, boolean reverse) throws IOException, MimetypeDetectionException {
		List<InputStream> streams = loadStreams(folder, reverse);
		List<ServerImage> images = new ArrayList<>(streams.size());
		for (InputStream stream : streams) {
			images.add(new ServerImage(stream));
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
		int sign = reverse ? -1 : 1;
		Reflections reflections = new Reflections(ClasspathHelper.forResource(folder), new ResourcesScanner());
		List<String> files = new ArrayList<>(reflections.getResources(f -> true));
		Collections.sort(files, (a, b) -> sign * a.compareToIgnoreCase(b));
		List<InputStream> images = new ArrayList<>(files.size());
		for (String file : files) {
			if (file.contains(folder)) {
				images.add(ServerImage.class.getResourceAsStream("/" + file));
			}
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
	public static BufferedImage read(ServerImage image) throws IOException {
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
	public static List<BufferedImage> read(List<ServerImage> images) throws IOException {
		List<BufferedImage> imgs = new ArrayList<>(images.size());
		for (ServerImage img : images) {
			imgs.add(read(img));
		}
		return imgs;
	}
}
