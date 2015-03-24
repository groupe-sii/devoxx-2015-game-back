package fr.sii.survival.core.domain.image;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import fr.sii.survival.core.exception.MimetypeDetectionException;

/**
 * Provide useful information for sprite. Can also generate a sprite if you
 * provide the images to combine into one. The image will be generated
 * horizontally. All images MUST have the same size.
 * 
 * The sprite image will be generated base64 encoded ({@link Base64ServerImage})
 * 
 * @author aurelien
 *
 */
public class Base64Sprite extends SpriteBase {

	public Base64Sprite(Base64ServerImage... images) throws IOException, MimetypeDetectionException {
		super(images);
	}

	public Base64Sprite(InputStream... images) throws IOException, MimetypeDetectionException {
		super(images);
	}

	public Base64Sprite(List<InputStream> images) throws IOException, MimetypeDetectionException {
		super(images);
	}

	public Base64Sprite(String folder, boolean reverse) throws IOException, MimetypeDetectionException {
		super(folder, reverse);
	}

	public Base64Sprite(String folder) throws IOException, MimetypeDetectionException {
		super(folder);
	}

	public Base64Sprite(UriImage... images) throws IOException, MimetypeDetectionException {
		super(images);
	}
	
}
