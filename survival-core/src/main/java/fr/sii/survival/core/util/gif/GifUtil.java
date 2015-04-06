package fr.sii.survival.core.util.gif;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageOutputStream;

import fr.sii.survival.core.domain.image.Base64ServerImage;
import fr.sii.survival.core.domain.image.ServerImage;
import fr.sii.survival.core.domain.image.UriImage;
import fr.sii.survival.core.exception.MimetypeDetectionException;
import fr.sii.survival.core.util.FileUtil;
import fr.sii.survival.core.util.ImageUtil;
import fr.sii.survival.core.util.gif.GifSequenceUtil.GifFrame;

public class GifUtil {
	public static final String GIF_GENERATION_FOLDER = "survival-gif";
	
	private GifUtil() {
		super();
	}

	public static ServerImage fromUriImages(List<UriImage> images, long delayBetweenFrames, int loops, GifWriter writer) throws IOException, MimetypeDetectionException {
		return generate(ImageUtil.readUriImages(images), delayBetweenFrames, loops, writer);
	}

	public static ServerImage fromBase64Images(List<Base64ServerImage> images, long delayBetweenFrames, int loops, GifWriter writer) throws IOException, MimetypeDetectionException {
		return generate(ImageUtil.read(images), delayBetweenFrames, loops, writer);
	}

	public static ServerImage fromStream(List<InputStream> images, long delayBetweenFrames, int loops, GifWriter writer) throws IOException {
		return generate(ImageUtil.load(images), delayBetweenFrames, loops, writer);
	}

	public static ServerImage generate(List<BufferedImage> images, long delayBetweenFrames, int loops, GifWriter writer) throws IOException {
		List<GifFrame> gifFrames = new ArrayList<GifFrame>();
		for (BufferedImage image : images) {
			int transparentColor = 0x000000;
			BufferedImage gif = GifSequenceUtil.convertRGBAToGIF(image, transparentColor);

			// make transparent pixels not 'shine through'
			String disposal = GifFrame.RESTORE_TO_BGCOLOR;

			// add frame to sequence
			gifFrames.add(new GifFrame(gif, delayBetweenFrames, disposal));
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GifSequenceUtil.saveAnimatedGIF(out, gifFrames, loops);
		return writer.write(out.toByteArray());
	}
}
