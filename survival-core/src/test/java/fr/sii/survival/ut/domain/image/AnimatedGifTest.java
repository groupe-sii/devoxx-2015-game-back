package fr.sii.survival.ut.domain.image;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import fr.sii.survival.core.domain.image.AnimatedGif;
import fr.sii.survival.core.domain.image.UriAnimatedGif;
import fr.sii.survival.core.exception.MimetypeDetectionException;

public class AnimatedGifTest {
	@Test
	public void uriGifFromFolder() throws IOException, MimetypeDetectionException {
		AnimatedGif gif = new UriAnimatedGif("images/lava", 30, 1);
		Assert.assertNotNull("Generated gif should not be null", gif);
	}
}
