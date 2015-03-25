package fr.sii.survival.config.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class StateChangeMixin {
	public StateChangeMixin(@JsonProperty("state") String state) {
		// nothing to do
	}

}
