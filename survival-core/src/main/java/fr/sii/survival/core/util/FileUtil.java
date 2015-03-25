package fr.sii.survival.core.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileUtil {
	private static Map<String, Path> directories = new HashMap<>();
	
	private FileUtil() {
		super();
	}
	
	public static Path getDirectory(String name) throws IOException {
		Path path = directories.get(name);
		if(path==null) {
			path = Files.createTempDirectory(name);
			// automatically purge the folder when exiting
			path.toFile().deleteOnExit();
			directories.put(name, path);
		}
		return path;
	}
	
	public static File getFile(String folder, String name) throws IOException {
		return new File(getDirectory(folder).toFile(), name);
	}
	
	public static File getRandomFile(String folder, String prefix, String suffix) throws IOException {
		return Files.createTempFile(getDirectory(folder), prefix, suffix).toFile();
	}
}
