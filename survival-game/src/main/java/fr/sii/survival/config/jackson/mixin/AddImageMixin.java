package fr.sii.survival.config.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.image.Image;

public abstract class AddImageMixin {
	public AddImageMixin(@JsonProperty("image") Image image, @JsonProperty("cell") Cell cell) {
		// nothing to do
	}
}
