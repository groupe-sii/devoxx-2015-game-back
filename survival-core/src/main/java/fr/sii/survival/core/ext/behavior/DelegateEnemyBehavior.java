package fr.sii.survival.core.ext.behavior;

import java.util.List;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;
import fr.sii.survival.core.service.board.BoardService;

/**
 * Basic enemy helper that simply delegates computation to:
 * <ul>
 * <li>an EnemyMoveManager for computing next enemy position</li>
 * <li>an EnemyActionManager for computing action to execute</li>
 * <li>a TargetManager for computing where the action must be executed</li>
 * </ul>
 * 
 * @author aurelien
 *
 */
public class DelegateEnemyBehavior {

	private Enemy enemy;

	private BoardService boardService;

	private EnemyMoveBehavior moveManager;

	private EnemyActionBehavior actionManager;

	private TargetBehavior targetManager;

	public DelegateEnemyBehavior(Enemy enemy, BoardService boardService, EnemyMoveBehavior moveManager, EnemyActionBehavior actionManager, TargetBehavior targetManager) {
		super();
		this.enemy = enemy;
		this.boardService = boardService;
		this.moveManager = moveManager;
		this.actionManager = actionManager;
		this.targetManager = targetManager;
	}

	public void run(GameContext context) throws GameException {
		// select the cells where to execute an action
		List<Cell> targets = targetManager.getTargetPositions(context);
		// execute the action on each targeted cell
		for (Cell target : targets) {
			actionManager.execute(context.getGame(), target);
		}
		// compute the position where the enemy will move to
		Cell next = moveManager.getNextPosition(context);
		// change the position of the enemy
		boardService.move(context.getBoard(), enemy, next);
	}
}
