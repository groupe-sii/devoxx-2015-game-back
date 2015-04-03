package fr.sii.survival.core.ext.behavior;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * @author Aurélien Baudet
 *
 */
public class DelegateEnemyBehavior {
	private static final Logger LOG = LoggerFactory.getLogger(DelegateEnemyBehavior.class);

	private Enemy enemy;

	private BoardService boardService;

	private EnemyMoveBehavior moveBehavior;

	private EnemyActionBehavior actionBehavior;

	private TargetBehavior targetBehavior;

	public DelegateEnemyBehavior(Enemy enemy, BoardService boardService, EnemyMoveBehavior moveBehavior, EnemyActionBehavior actionBehavior, TargetBehavior targetBehavior) {
		super();
		this.enemy = enemy;
		this.boardService = boardService;
		this.moveBehavior = moveBehavior;
		this.actionBehavior = actionBehavior;
		this.targetBehavior = targetBehavior;
	}

	public void run(GameContext context) throws GameException {
		// select the cells where to execute an action
		List<Cell> targets = getTargetBehavior().getTargetPositions(context);
		// execute the action on each targeted cell
		for (Cell target : targets) {
			getActionBehavior().execute(context.getGame(), target);
		}
		// compute the position where the enemy will move to
		Cell next = getMoveBehavior().getNextPosition(context);
		// change the position of the enemy
		boardService.move(context.getBoard(), enemy, next);
	}
	
	private TargetBehavior getTargetBehavior() {
		if(targetBehavior==null) {
			LOG.warn("{}: target behavior is null so no action will be executed", enemy);
			return (c) -> new ArrayList<>();
		} else {
			return targetBehavior;
		}
	}
	
	private EnemyActionBehavior getActionBehavior() {
		if(actionBehavior==null) {
			LOG.warn("{}: action behavior is null so no action will be executed", enemy);
			return (g, c) -> {};
		} else {
			return actionBehavior;
		}
	}	
	private EnemyMoveBehavior getMoveBehavior() {
		if(moveBehavior==null) {
			LOG.warn("{}: move behavior is null so your enemy will never move", enemy);
			return (c) -> c.getBoard().getCell(enemy);
		} else {
			return moveBehavior;
		}
	}
}
