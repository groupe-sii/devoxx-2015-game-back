package fr.sii.survival.core.ext.behavior.target;

import java.util.Arrays;
import java.util.List;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.ext.GameContext;

/**
 * Stupid manager that just targets a cell on the board without checking if
 * there is at least one player on it or not.
 * 
 * @author aurelien
 *
 */
public class RandomCellTargetBehavior implements TargetBehavior {

	@Override
	public List<Cell> getTargetPositions(GameContext context) {
		int x = (int) Math.floor(Math.random() * (context.getBoard().getWidth()-1));
		int y = (int) Math.floor(Math.random() * (context.getBoard().getHeight()-1));
		return Arrays.asList(new Cell(x, y));
	}

}
