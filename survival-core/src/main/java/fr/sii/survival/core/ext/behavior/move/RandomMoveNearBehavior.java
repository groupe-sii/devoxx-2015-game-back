package fr.sii.survival.core.ext.behavior.move;

import java.util.function.Supplier;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.ext.GameContext;

/**
 * Move manager that places the enemy one case near to the current one. The
 * enemy can only move in one direction at a time (x axis or y axis but not
 * both).
 * 
 * @author Aurélien Baudet
 *
 */
public class RandomMoveNearBehavior implements EnemyMoveBehavior {

	@Override
	public Cell getNextPosition(GameContext context) {
		int axis = (int) Math.round(Math.random());
		Cell current = context.getCell();
		Board board = context.getBoard();
		Supplier<Integer> axisSupplier = axis==0 ? current::getX : current::getY;
		Supplier<Integer> sizeSupplier = axis==0 ? board::getWidth : board::getHeight;
		int direction = (int) Math.round(2 * Math.random()) - 1;
		int newPos = axisSupplier.get() + direction;
		if (newPos < 0) {
			newPos = 0;
		} else if (newPos >= sizeSupplier.get()) {
			newPos = sizeSupplier.get() - 1;
		}
		return axis==0 ? new Cell(newPos, current.getY()) : new Cell(current.getX(), newPos);
	}

}
