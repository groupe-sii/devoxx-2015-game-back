package fr.sii.survival.core.ext;

import java.util.List;

import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.ext.behavior.action.EnemyActionManager;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveManager;
import fr.sii.survival.core.ext.behavior.target.TargetManager;
import fr.sii.survival.core.service.action.ActionService;

/**
 * Basic enemy extension that simply delegates computation to:
 * <ul>
 * <li>an EnemyMoveManager for computing next enemy position</li>
 * <li>an EnemyActionManager for computing action to execute</li>
 * <li>a TargetManager for computing where the action must be executed</li>
 * </ul>
 * 
 * @author aurelien
 *
 */
public class DelegateEnemyManager extends EnemyExtension {

	private EnemyMoveManager moveManager;

	private EnemyActionManager actionManager;

	private TargetManager targetManager;

	public DelegateEnemyManager(EnemyMoveManager moveManager, EnemyActionManager actionManager, TargetManager targetManager) {
		super();
		this.moveManager = moveManager;
		this.actionManager = actionManager;
		this.targetManager = targetManager;
	}

	public void run(GameContext context) throws GameException {
		// compute the position where the enemy will be moved to
		Cell next = moveManager.getNextPosition(context);
		// change the position
		actionService.execute(new ChangePosition(context.getCell(), next));
		// select the cells where to execute an action
		List<Cell> targets = targetManager.getTargetPositions(context);
		// execute the action on each targeted cell
		for (Cell target : targets) {
			actionManager.execute(target);
		}
	}
}
