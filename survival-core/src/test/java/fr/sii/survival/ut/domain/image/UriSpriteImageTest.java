package fr.sii.survival.ut.domain.image;

import java.awt.Dimension;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import fr.sii.survival.core.domain.image.Base64ServerImage;
import fr.sii.survival.core.domain.image.Sprite;
import fr.sii.survival.core.domain.image.UriImage;
import fr.sii.survival.core.domain.image.UriSprite;
import fr.sii.survival.core.exception.MimetypeDetectionException;

public class UriSpriteImageTest {
	@Test
	public void folder() throws IOException, MimetypeDetectionException {
		Sprite sprite = new UriSprite("images/immobilize");
		Assert.assertEquals("sprite size should be 306x32", new Dimension(306, 32), new Dimension(sprite.getWidth(), sprite.getHeight()));
		Assert.assertEquals("sprite frame size should be 34x32", new Dimension(34, 32), new Dimension(sprite.getFrameWidth(), sprite.getFrameHeight()));
	}
	
	@Test
	public void base64Images() throws IOException, MimetypeDetectionException {
		Sprite sprite = new UriSprite(new Base64ServerImage("images/immobilize/1.png"), new Base64ServerImage("images/immobilize/2.png"));
		Assert.assertEquals("sprite size should be 68x32", new Dimension(68, 32), new Dimension(sprite.getWidth(), sprite.getHeight()));
		Assert.assertEquals("sprite frame size should be 34x32", new Dimension(34, 32), new Dimension(sprite.getFrameWidth(), sprite.getFrameHeight()));
	}

	@Test
	public void uriImages() throws IOException, MimetypeDetectionException, URISyntaxException {
		Sprite sprite = new UriSprite(new UriImage("images/immobilize/1.png"), new UriImage("images/immobilize/2.png"));
		Assert.assertEquals("sprite size should be 68x32", new Dimension(68, 32), new Dimension(sprite.getWidth(), sprite.getHeight()));
		Assert.assertEquals("sprite frame size should be 34x32", new Dimension(34, 32), new Dimension(sprite.getFrameWidth(), sprite.getFrameHeight()));
	}

}
