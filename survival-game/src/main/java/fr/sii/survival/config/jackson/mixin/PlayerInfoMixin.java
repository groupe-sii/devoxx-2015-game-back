package fr.sii.survival.config.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class PlayerInfoMixin {
	public PlayerInfoMixin(@JsonProperty("name") String name) {
		// nothing to do
	}
}
