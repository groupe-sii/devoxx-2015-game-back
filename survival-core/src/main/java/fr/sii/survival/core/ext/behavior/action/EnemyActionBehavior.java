package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.GameException;

/**
 * An enemy can execute actions. To help enemy definition, simplified AI can be
 * used. This is done through implementation of simple and fixed behaviors.
 * 
 * @author Aurélien Baudet
 *
 */
@FunctionalInterface
public interface EnemyActionBehavior {
	/**
	 * Execute an action on a cell of the provided game
	 * 
	 * @param game
	 *            the game to execute action on
	 * @param cell
	 *            the target cell where the action will be executed
	 * @throws GameException
	 *             when the action couldn't be executed
	 */
	public void execute(Game game, Cell cell) throws GameException;
}
