package fr.sii.survival.core.ext.behavior.move;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.ext.GameContext;

/**
 * Move manager that places the enemy on a random cell on the board.
 * 
 * @author aurelien
 *
 */
public class RandomCellManager implements EnemyMoveManager {

	@Override
	public Cell getNextPosition(GameContext context) {
		int newX = (int) Math.floor((context.getBoard().getWidth()-1)*Math.random());
		int newY = (int) Math.floor((context.getBoard().getHeight()-1)*Math.random());
		return new Cell(newX, newY);
	}

}
