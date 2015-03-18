package fr.sii.survival.core.ext.behavior.action;

import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.ext.behavior.action.shape.Shape;

/**
 * Action manager that applies the delegate action to the provided area.
 * The area computation is provided by the shape.
 * 
 * @author aurelien
 *
 */
public class AreaActionBehavior implements EnemyActionBehavior {

	/**
	 * The action to execute on cells contained by the shape
	 */
	private EnemyActionBehavior delegate;
	
	/**
	 * The area shape
	 */
	private Shape shape;
	
	public AreaActionBehavior(EnemyActionBehavior delegate, Shape shape) {
		super();
		this.delegate = delegate;
		this.shape = shape;
	}

	@Override
	public void execute(Game game, Cell origin) throws GameException {
		// compute area using shape
		List<Cell> cells = shape.getCells(origin);
		// execute several times the delegate
		for(Cell cell : cells) {
			delegate.execute(game, cell);
		}
	}

}
