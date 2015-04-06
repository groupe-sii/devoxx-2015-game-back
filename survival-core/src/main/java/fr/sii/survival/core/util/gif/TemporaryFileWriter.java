package fr.sii.survival.core.util.gif;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

import fr.sii.survival.core.domain.image.ServerImage;
import fr.sii.survival.core.domain.image.UriImage;
import fr.sii.survival.core.util.FileUtil;

public class TemporaryFileWriter implements GifWriter {

	@Override
	public ServerImage write(byte[] bytes) throws IOException {
		File tempFile = FileUtil.getRandomFile(GifUtil.GIF_GENERATION_FOLDER, "anim", ".gif");
		Files.write(bytes, tempFile);
		return new UriImage(tempFile);
	}

}
