package fr.sii.survival.core.util.sprite;

import java.awt.Dimension;

import fr.sii.survival.core.domain.image.ServerImage;

public class ServerSprite {
	private ServerImage image;
	
	private Dimension imageSize;

	private Dimension frameSize;

	public ServerSprite(ServerImage image, Dimension imageSize, Dimension frameSize) {
		super();
		this.image = image;
		this.imageSize = imageSize;
		this.frameSize = frameSize;
	}

	public ServerImage getImage() {
		return image;
	}

	public Dimension getImageSize() {
		return imageSize;
	}

	public Dimension getFrameSize() {
		return frameSize;
	}
}