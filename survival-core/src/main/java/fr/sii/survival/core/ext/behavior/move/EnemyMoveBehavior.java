package fr.sii.survival.core.ext.behavior.move;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.ext.GameContext;

public interface EnemyMoveBehavior {
	public Cell getNextPosition(GameContext context);
}
