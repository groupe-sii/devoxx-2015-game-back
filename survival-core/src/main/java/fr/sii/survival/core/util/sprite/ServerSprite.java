package fr.sii.survival.core.util.sprite;

import java.awt.Dimension;

import fr.sii.survival.core.domain.image.ServerImage;

public class ServerSprite {
	private ServerImage image;
	
	private Dimension imageSize;

	public ServerSprite(ServerImage image, Dimension imageSize) {
		super();
		this.image = image;
		this.imageSize = imageSize;
	}

	public ServerImage getImage() {
		return image;
	}

	public Dimension getImageSize() {
		return imageSize;
	}
}