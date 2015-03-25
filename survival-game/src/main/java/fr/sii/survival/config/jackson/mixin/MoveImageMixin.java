package fr.sii.survival.config.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.image.Image;

public class MoveImageMixin {
	public MoveImageMixin(@JsonProperty("image") Image image, @JsonProperty("start") Cell start, @JsonProperty("end") Cell end) {
		// nothing to do
	}
}
