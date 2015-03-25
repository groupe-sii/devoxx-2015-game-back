package fr.sii.survival.config.jackson;

import java.io.IOException;
import java.net.URI;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import fr.sii.survival.config.StaticResourcesConfig;
import fr.sii.survival.core.util.FileUtil;

public class FileToRelativeUrlSerializer extends JsonSerializer<URI> {

	@Override
	public void serialize(URI value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		// replace classpath folder that contains images to relative URL
		String relative = value.toString().replaceFirst("^.*"+StaticResourcesConfig.STATIC_RESOURCE_FOLDER_PATH, "");
		// replace temporary sprite folder absolute path to relative URL
		relative = relative.replace("file:", "").replace(FileUtil.getDirectory(StaticResourcesConfig.SPRITE_GENERATION_FOLDER).toAbsolutePath().toString(), "");
		jgen.writeString("/"+StaticResourcesConfig.STATIC_RESOURCE_URL_PREFIX+relative);
	}

}
