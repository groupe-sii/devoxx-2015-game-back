package fr.sii.survival.ut.domain.image;

import java.awt.Dimension;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import fr.sii.survival.core.domain.image.ServerImage;
import fr.sii.survival.core.domain.image.Sprite;
import fr.sii.survival.core.exception.MimetypeDetectionException;

public class SpriteImageTest {
	@Test
	public void folder() throws IOException, MimetypeDetectionException {
		Sprite sprite = new Sprite("images/immobilize");
		Assert.assertEquals("sprite size should be 306x32", new Dimension(306, 32), new Dimension(sprite.getWidth(), sprite.getHeight()));
	}
	
	@Test
	public void serverImages() throws IOException, MimetypeDetectionException {
		Sprite sprite = new Sprite(new ServerImage("images/immobilize/1.png"), new ServerImage("images/immobilize/2.png"));
		Assert.assertEquals("sprite size should be 68x32", new Dimension(68, 32), new Dimension(sprite.getWidth(), sprite.getHeight()));
	}

}
