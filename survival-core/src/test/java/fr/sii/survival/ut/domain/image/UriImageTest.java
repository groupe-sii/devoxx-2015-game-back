package fr.sii.survival.ut.domain.image;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import fr.sii.survival.core.domain.image.UriImage;
import fr.sii.survival.core.exception.MimetypeDetectionException;

public class UriImageTest {
	@Test
	public void relative() throws IOException, MimetypeDetectionException, URISyntaxException {
		UriImage image = new UriImage("images/Green-Monster-icon.png");
		Assert.assertNotNull("image uri should not be null", image.getUri());
	}
	
	@Test
	public void gif() throws IOException, MimetypeDetectionException, URISyntaxException {
		UriImage image = new UriImage("images/worldboss.gif");
		Assert.assertNotNull("image uri should not be null", image.getUri());
	}
	
	@Test
	public void png() throws IOException, MimetypeDetectionException, URISyntaxException {
		UriImage image = new UriImage("images/worldboss.png");
		Assert.assertNotNull("image uri should not be null", image.getUri());
	}
	
	@Test(expected=IOException.class)
	public void relativeNotFound() throws IOException, MimetypeDetectionException, URISyntaxException {
		new UriImage("images/doesntexist.png");
	}
}
