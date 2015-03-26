package fr.sii.survival.config.jackson.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.board.Cell;

public abstract class UpdateStatesMixin {
	public UpdateStatesMixin(@JsonProperty("cell") Cell cell, @JsonProperty("stateChanges") List<StateChange> stateChanges) {
		// nothing to do
	}
}
