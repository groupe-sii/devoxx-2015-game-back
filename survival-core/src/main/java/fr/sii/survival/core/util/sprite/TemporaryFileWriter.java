package fr.sii.survival.core.util.sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.sii.survival.core.domain.image.ServerImage;
import fr.sii.survival.core.domain.image.UriImage;
import fr.sii.survival.core.util.FileUtil;
import fr.sii.survival.core.util.SpriteUtil;

public class TemporaryFileWriter implements SpriteWriter {

	@Override
	public ServerImage write(BufferedImage image) throws IOException {
		File file = FileUtil.getRandomFile(SpriteUtil.SPRITE_GENERATION_FOLDER, "sprite", ".png");
		ImageIO.write(image, "png", file);
		return new UriImage(file);
	}

}
