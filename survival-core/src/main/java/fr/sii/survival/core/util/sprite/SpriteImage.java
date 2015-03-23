package fr.sii.survival.core.util.sprite;

import java.awt.image.BufferedImage;
import java.util.List;

public class SpriteImage {
	private List<BufferedImage> frames;
	
	private BufferedImage sprite;

	public SpriteImage(BufferedImage sprite, List<BufferedImage> frames) {
		super();
		this.frames = frames;
		this.sprite = sprite;
	}

	public List<BufferedImage> getFrames() {
		return frames;
	}

	public BufferedImage getSprite() {
		return sprite;
	}
}