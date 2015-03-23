package fr.sii.survival.ut.domain.image;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import fr.sii.survival.core.domain.image.Animation;
import fr.sii.survival.core.exception.MimetypeDetectionException;

public class AnimationTest {
	@Test
	public void folder() throws IOException, MimetypeDetectionException {
		Animation animation = new Animation("images/immobilize", 100);
		Assert.assertEquals("animation should contain 9 images", 9, animation.getImages().size());
	}
}
