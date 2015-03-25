package fr.sii.survival.config.jackson.mixin;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import fr.sii.survival.config.jackson.FileToRelativeUrlSerializer;

public abstract class UriImageMixin {
	public UriImageMixin(@JsonProperty("uri") URI uri) {
		// nothing to do
	}
	
	@JsonSerialize(using=FileToRelativeUrlSerializer.class)
	public abstract URI getUri();
}
