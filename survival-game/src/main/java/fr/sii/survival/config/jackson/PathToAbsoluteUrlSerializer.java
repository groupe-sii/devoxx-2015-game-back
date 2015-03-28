package fr.sii.survival.config.jackson;

import java.io.IOException;
import java.net.URI;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import fr.sii.survival.util.ImageUrlUtil;

public class PathToAbsoluteUrlSerializer extends JsonSerializer<URI> {

	@Override
	public void serialize(URI value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeString(ImageUrlUtil.absoluteUrl(value).toString());
	}

}
