package fr.sii.survival.core.ext;

import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.ext.behavior.DelegateEnemyManager;
import fr.sii.survival.core.ext.behavior.action.EnemyActionManager;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveManager;
import fr.sii.survival.core.ext.behavior.target.TargetManager;

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

	protected DelegateEnemyExtension(String name, String avatar, int life) {
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
	protected abstract EnemyActionManager getActionManager(GameContext context);

	/**
	 * Provide the move manager (how this enemy should move)
	 * 
	 * @param context
	 *            the current context of the game (game, players, board)
	 * @return the move manager instance
	 */
	protected abstract EnemyMoveManager getMoveManager(GameContext context);

	/**
	 * Provide the target manager (how to target players)
	 * 
	 * @param context
	 *            the current context of the game (game, players, board)
	 * @return the target manager instance
	 */
	protected abstract TargetManager getTargetManager(GameContext context);

	@Override
	public void run(GameContext context) throws GameException {
		DelegateEnemyManager simpleIAEnemyManager = new DelegateEnemyManager(actionService, getMoveManager(context), getActionManager(context), getTargetManager(context));
		simpleIAEnemyManager.run(context);
	}

}
