package fr.sii.survival.config.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleLifeMixin {
	public SimpleLifeMixin(@JsonProperty("max") int max) {
		// nothing to do
	}
}
