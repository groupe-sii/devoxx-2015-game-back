package fr.sii.survival.ut.domain.image;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import fr.sii.survival.core.domain.image.Base64ServerImage;
import fr.sii.survival.core.exception.MimetypeDetectionException;

public class ServerImageTest {
	@Test
	public void relative() throws IOException, MimetypeDetectionException {
		Base64ServerImage image = new Base64ServerImage("images/Green-Monster-icon.png");
		String expected = IOUtils.toString(getClass().getResourceAsStream("/base64/Green-Monster-icon.txt"));
		Assert.assertEquals("base64 encoding should give same result", expected, image.getContent());
		Assert.assertEquals("mimetype should be image/png", "image/png", image.getMimetype());
	}
	
	@Test
	public void gif() throws IOException, MimetypeDetectionException {
		Base64ServerImage image = new Base64ServerImage("images/worldboss.gif");
		Assert.assertEquals("mimetype should be image/png", "image/gif", image.getMimetype());
	}
	
	@Test
	public void png() throws IOException, MimetypeDetectionException {
		Base64ServerImage image = new Base64ServerImage("images/worldboss.png");
		Assert.assertEquals("mimetype should be image/png", "image/png", image.getMimetype());
	}
	
	@Test(expected=IOException.class)
	public void relativeNotFound() throws IOException, MimetypeDetectionException {
		new Base64ServerImage("images/doesntexist.png");
	}
}
