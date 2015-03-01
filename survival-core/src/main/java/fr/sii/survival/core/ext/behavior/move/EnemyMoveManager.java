package fr.sii.survival.core.ext.behavior.move;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.ext.GameContext;

public interface EnemyMoveManager {
	public Cell getNextPosition(GameContext context);
}
