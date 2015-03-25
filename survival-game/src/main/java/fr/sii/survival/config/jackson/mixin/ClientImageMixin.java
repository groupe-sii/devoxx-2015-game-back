package fr.sii.survival.config.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ClientImageMixin {
	public ClientImageMixin(@JsonProperty("name") String name) {
		// nothing to do
	}
	

}
