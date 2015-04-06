package fr.sii.survival.core.domain.image;

/**
 * Interface for all animated gif images.
 * 
 * @author Aurélien Baudet
 *
 */
public interface AnimatedGif extends ServerImage {
	/**
	 * Get the id of the image
	 * 
	 * @return the id of the image
	 */
	public String getId();

	/**
	 * Get the animated gif image located on the server side
	 * 
	 * @return the animated gif image
	 */
	public ServerImage getImage();

}