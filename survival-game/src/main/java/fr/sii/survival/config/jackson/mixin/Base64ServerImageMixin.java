package fr.sii.survival.config.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Base64ServerImageMixin {
	public Base64ServerImageMixin(@JsonProperty("content") byte[] content, @JsonProperty("mimetype") String mimetype) {
		// nothing to do
	}
}
