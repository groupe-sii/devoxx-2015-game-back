package fr.sii.survival.core.ext.behavior.target;

import java.util.List;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.ext.GameContext;

public interface TargetBehavior {
	public List<Cell> getTargetPositions(GameContext context);
}
