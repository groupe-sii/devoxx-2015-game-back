package fr.sii.survival.core.ext.behavior.move;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.ext.GameContext;

/**
 * An enemy can move. To help move definition, simplified AI can be used. This
 * is done through implementation of simple and fixed behaviors.
 * 
 * @author Aurélien Baudet
 *
 */
@FunctionalInterface
public interface EnemyMoveBehavior {
	/**
	 * Provide the cell where the enemy should move to
	 * 
	 * @param context
	 *            the current context of the game
	 * @return the cell where the enemy should go
	 */
	public Cell getNextPosition(GameContext context);
}
