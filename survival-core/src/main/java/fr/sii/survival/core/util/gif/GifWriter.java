package fr.sii.survival.core.util.gif;

import java.io.IOException;

import fr.sii.survival.core.domain.image.ServerImage;

/**
 * Strategy to write a animated gif image and convert it to an image representation
 * that is usable by the game
 * 
 * @author Aurélien Baudet
 *
 */
public interface GifWriter {
	/**
	 * Write the in memory image and convert it to representation of the image
	 * usable by the game
	 * 
	 * @param image
	 *            the in memory image
	 * @return the image representation for being handled by the game
	 * @throws IOException
	 *             when the image couldn't be written
	 */
	public ServerImage write(byte[] bytes) throws IOException;
}
