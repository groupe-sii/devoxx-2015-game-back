package fr.sii.survival.core.ext.behavior.target;

import java.util.List;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.ext.GameContext;

/**
 * An enemy can target cells. To help targeting definition, simplified AI can be
 * used. This is done through implementation of simple and fixed behaviors.
 * Players can be targeted indirectly by their position on the board.
 * 
 * @author Aurélien Baudet
 *
 */
@FunctionalInterface
public interface TargetBehavior {
	/**
	 * Provide the list of cells to target.
	 * 
	 * @param context
	 *            the current game context
	 * @return the list of cells to target
	 */
	public List<Cell> getTargetPositions(GameContext context);
}
