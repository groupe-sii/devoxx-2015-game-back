package fr.sii.survival.core.domain.image;

/**
 * Interface for all images that are a composition of sub images.
 * 
 * @author aurelien
 *
 */
public interface Sprite extends Image {
	/**
	 * Get the id of the sprite
	 * 
	 * @return the id of the sprite
	 */
	public String getId();

	/**
	 * Get the sprite image located on the server side
	 * 
	 * @return the sprite image
	 */
	public ServerImage getImage();

	/**
	 * Get the whole width of the sprite image
	 * 
	 * @return the width of the sprite image
	 */
	public int getWidth();

	/**
	 * Get the whole height of the sprite image
	 * 
	 * @return the height of the sprite image
	 */
	public int getHeight();

	/**
	 * Get the width of a single image that compose the sprite (all frames have the same size)
	 * 
	 * @return the width of a single image
	 */
	public int getFrameWidth();

	/**
	 * Get the height of a single image that compose the sprite (all frames have the same size)
	 * 
	 * @return the height of a single image
	 */
	public int getFrameHeight();

}