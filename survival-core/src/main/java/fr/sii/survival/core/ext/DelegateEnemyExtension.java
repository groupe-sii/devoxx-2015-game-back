package fr.sii.survival.core.ext;

import fr.sii.survival.core.domain.image.Image;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.ext.behavior.DelegateEnemyBehavior;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;

/**
 * Template implementation that delegates IA to provided managers
 * 
 * @author aurelien
 *
 */
public abstract class DelegateEnemyExtension extends EnemyExtension {

	protected DelegateEnemyExtension(Enemy enemy) {
		super(enemy);
	}

	protected DelegateEnemyExtension(String name, Image avatar, int life) {
		super(name, avatar, life);
	}

	@Override
	public void init() {
		// nothing to do
	}

	/**
	 * Provide the action manager (how to execute actions on cells or players)
	 * 
	 * @param context
	 *            the current context of the game (game, players, board)
	 * @return the action manager instance
	 */
	protected abstract EnemyActionBehavior getActionBehavior(GameContext context);

	/**
	 * Provide the move manager (how this enemy should move)
	 * 
	 * @param context
	 *            the current context of the game (game, players, board)
	 * @return the move manager instance
	 */
	protected abstract EnemyMoveBehavior getMoveBehavior(GameContext context);

	/**
	 * Provide the target manager (how to target players)
	 * 
	 * @param context
	 *            the current context of the game (game, players, board)
	 * @return the target manager instance
	 */
	protected abstract TargetBehavior getTargetBehavior(GameContext context);

	@Override
	public void run(GameContext context) throws GameException {
		DelegateEnemyBehavior simpleIAEnemyManager = new DelegateEnemyBehavior(getEnemy(), boardService, getMoveBehavior(context), getActionBehavior(context), getTargetBehavior(context));
		simpleIAEnemyManager.run(context);
	}

}
