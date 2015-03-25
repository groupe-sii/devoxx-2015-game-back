package fr.sii.survival.config.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.sii.survival.core.domain.board.Cell;

public abstract class UpdateLifeMixin {
	public UpdateLifeMixin(@JsonProperty("increment") int increment, @JsonProperty("cell") Cell cell) {
		// nothing to do
	}
}
