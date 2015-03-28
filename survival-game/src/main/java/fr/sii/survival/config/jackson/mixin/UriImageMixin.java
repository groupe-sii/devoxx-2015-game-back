package fr.sii.survival.config.jackson.mixin;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import fr.sii.survival.config.jackson.PathToAbsoluteUrlSerializer;

public abstract class UriImageMixin {
	public UriImageMixin(@JsonProperty("uri") URI uri) {
		// nothing to do
	}
	
	@JsonSerialize(using=PathToAbsoluteUrlSerializer.class)
	public abstract URI getUri();
}
