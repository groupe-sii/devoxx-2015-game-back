package fr.sii.survival.core.util.gif;

import java.io.IOException;

import fr.sii.survival.core.domain.image.Base64ServerImage;
import fr.sii.survival.core.domain.image.ServerImage;
import fr.sii.survival.core.exception.MimetypeDetectionException;

public class Base64Writer implements GifWriter {

	@Override
	public ServerImage write(byte[] bytes) throws IOException {
		try {
			return new Base64ServerImage(bytes);
		} catch (MimetypeDetectionException e) {
			throw new IOException("Failed to generate animated gif image base64 encoded", e);
		}
	}

}
