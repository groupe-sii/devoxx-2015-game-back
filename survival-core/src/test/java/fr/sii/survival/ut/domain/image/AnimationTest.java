package fr.sii.survival.ut.domain.image;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import fr.sii.survival.core.domain.animation.SpriteAnimation;
import fr.sii.survival.core.domain.image.Sprite;
import fr.sii.survival.core.exception.MimetypeDetectionException;

public class AnimationTest {
	@Test
	public void sprite() throws IOException, MimetypeDetectionException {
		SpriteAnimation animation = new SpriteAnimation("immobilized", 5000, new Sprite("images/immobilize"));
		Assert.assertEquals("animation should contain 9 frames", 9, animation.getFrames().size());
	}
	
	@Test(expected=IOException.class)
	public void spriteNoImage() throws IOException, MimetypeDetectionException {
		new SpriteAnimation("immobilized", 5000, new Sprite(""));
	}
}
