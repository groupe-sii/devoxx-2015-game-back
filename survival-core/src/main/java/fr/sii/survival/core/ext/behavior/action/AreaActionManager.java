package fr.sii.survival.core.ext.behavior.action;

import java.util.List;

import fr.sii.survival.core.domain.board.Cell;

/**
 * Action manager that applies the delegate action to the provided area.
 * The area computation is provided by the shape.
 * 
 * @author aurelien
 *
 */
public class AreaActionManager implements EnemyActionManager {

	/**
	 * The action to execute on cells contained by the shape
	 */
	private EnemyActionManager delegate;
	
	/**
	 * The area shape
	 */
	private Shape shape;
	
	public AreaActionManager(EnemyActionManager delegate, Shape shape) {
		super();
		this.delegate = delegate;
		this.shape = shape;
	}

	@Override
	public void execute(Cell origin) {
		// compute area using shape
		List<Cell> cells = shape.getCells(origin);
		// execute several times the delegate
		for(Cell cell : cells) {
			delegate.execute(cell);
		}
	}

}
