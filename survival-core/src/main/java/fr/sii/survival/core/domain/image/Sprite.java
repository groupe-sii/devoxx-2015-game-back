package fr.sii.survival.core.domain.image;

public interface Sprite extends Image {

	public String getId();

	public ServerImage getImage();

	public int getWidth();

	public int getHeight();

	public int getFrameWidth();

	public int getFrameHeight();

}