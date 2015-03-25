package fr.sii.survival.config.jackson.mixin;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UriImageMixin {
	public UriImageMixin(@JsonProperty("uri") URI uri) {
		// nothing to do
	}
}
