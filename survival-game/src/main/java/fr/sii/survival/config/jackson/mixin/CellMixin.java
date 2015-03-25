package fr.sii.survival.config.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CellMixin {
	public CellMixin(@JsonProperty("x") int x, @JsonProperty("y") int y) {
		// nothing to do
	}
}
