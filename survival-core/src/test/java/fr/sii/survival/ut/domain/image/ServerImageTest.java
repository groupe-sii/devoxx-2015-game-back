package fr.sii.survival.ut.domain.image;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import fr.sii.survival.core.domain.image.ServerImage;

public class ServerImageTest {
	@Test
	public void relative() throws IOException {
		ServerImage image = new ServerImage("images/Green-Monster-icon.png");
		byte[] expected = IOUtils.toByteArray(getClass().getResourceAsStream("/base64/Green-Monster-icon.txt"));
		Assert.assertArrayEquals("base64 encoding should give same result", expected, image.getContent());
	}
	
	@Test(expected=IOException.class)
	public void relativeNotFound() throws IOException {
		new ServerImage("images/doesntexist.png");
	}
}
