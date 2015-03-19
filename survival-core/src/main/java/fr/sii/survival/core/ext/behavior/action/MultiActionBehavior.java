package fr.sii.survival.core.ext.behavior.action;

import java.util.Arrays;
import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.GameException;

/**
 * Decorator that executes several delegate behaviors
 * 
 * @author aurelien
 *
 */
public class MultiActionBehavior implements EnemyActionBehavior {

	private List<EnemyActionBehavior> actions;

	public MultiActionBehavior(List<EnemyActionBehavior> actions) {
		super();
		this.actions = actions;
	}

	public MultiActionBehavior(EnemyActionBehavior... actions) {
		this(Arrays.asList(actions));
	}

	@Override
	public void execute(Game game, Cell cell) throws GameException {
		for (EnemyActionBehavior action : actions) {
			action.execute(game, cell);
		}
	}

}
