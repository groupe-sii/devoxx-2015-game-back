package fr.sii.survival.core.util.sprite;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.sii.survival.core.domain.image.Base64ServerImage;
import fr.sii.survival.core.domain.image.ServerImage;
import fr.sii.survival.core.exception.MimetypeDetectionException;

public class Base64Writer implements SpriteWriter {

	@Override
	public ServerImage write(BufferedImage image) throws IOException {
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImageIO.write(image, "png", output);
			return new Base64ServerImage(output.toByteArray());
		} catch (MimetypeDetectionException e) {
			throw new IOException("Failed to generate sprite image base64 encoded", e);
		}
	}

}
