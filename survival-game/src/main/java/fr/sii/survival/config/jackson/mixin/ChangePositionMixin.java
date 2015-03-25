package fr.sii.survival.config.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.sii.survival.core.domain.board.Cell;

public abstract class ChangePositionMixin {
	public ChangePositionMixin(@JsonProperty("start") Cell start, @JsonProperty("end") Cell end) {
		// nothing to do
	}
}
