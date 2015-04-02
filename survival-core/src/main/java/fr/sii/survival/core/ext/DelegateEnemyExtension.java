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
 * @author Aurélien Baudet
 *
 */
public abstract class DelegateEnemyExtension extends EnemyExtension {

	/**
	 * Reload context every execution or always reuse the same behaviors
	 */
	protected boolean reload;

	private DelegateEnemyBehavior delegateEnemyBehavior;

	/**
	 * Initialize the enemy extension with the provided managed enemy. This
	 * extension delegates the behavior of the enemy to specialized behaviors
	 * provided by implementing abstract methods.
	 * 
	 * The specialized behaviors won't be reloaded every execution of run
	 * method.
	 * 
	 * @param enemy
	 *            the enemy managed by the extension
	 */
	protected DelegateEnemyExtension(Enemy enemy) {
		this(enemy, false);
	}

	/**
	 * Initialize the enemy extension with the provided managed enemy. This
	 * extension delegates the behavior of the enemy to specialized behaviors
	 * provided by implementing abstract methods.
	 * 
	 * The specialized behaviors won't be reloaded every execution of run
	 * method.
	 * 
	 * @param name
	 *            the name of the enemy managed by the extension
	 * @param avatar
	 *            the image of the enemy managed by the extension
	 * @param life
	 *            the maximum points of life of the enemy managed by the
	 *            extension
	 */
	protected DelegateEnemyExtension(String name, Image avatar, int life) {
		this(name, avatar, life, false);
	}

	/**
	 * Initialize the enemy extension with the provided managed enemy. This
	 * extension delegates the behavior of the enemy to specialized behaviors
	 * provided by implementing abstract methods.
	 * 
	 * @param enemy
	 *            the enemy managed by the extension
	 * @param reload
	 *            if true, then abstract methods that provide specialized
	 *            behaviors are called on every invocation of run method
	 */
	protected DelegateEnemyExtension(Enemy enemy, boolean reload) {
		super(enemy);
		this.reload = reload;
	}

	/**
	 * Initialize the enemy extension with the provided managed enemy. This
	 * extension delegates the behavior of the enemy to specialized behaviors
	 * provided by implementing abstract methods.
	 * 
	 * @param name
	 *            the name of the enemy managed by the extension
	 * @param reload
	 *            if true, then abstract methods that provide specialized
	 *            behaviors are called on every invocation of run method
	 * @param avatar
	 *            the image of the enemy managed by the extension
	 * @param life
	 *            the maximum points of life of the enemy managed by the
	 *            extension
	 */
	protected DelegateEnemyExtension(String name, Image avatar, int life, boolean reload) {
		super(name, avatar, life);
		this.reload = reload;
	}

	@Override
	public void init() {
		// nothing to do
	}

	/**
	 * Provide the action manager (how to execute actions on cells or players)
	 * 
	 * @param context
	 *            the current context of the game (game, players, board). If
	 *            reload is disabled, then the context is never refreshed
	 * @return the action manager instance
	 * @throws GameException
	 *             when unexpected exception is raised
	 */
	protected abstract EnemyActionBehavior getActionBehavior(GameContext context) throws GameException;

	/**
	 * Provide the move manager (how this enemy should move)
	 * 
	 * @param context
	 *            the current context of the game (game, players, board). If
	 *            reload is disabled, then the context is never refreshed
	 * @return the move manager instance
	 * @throws GameException
	 *             when unexpected exception is raised
	 */
	protected abstract EnemyMoveBehavior getMoveBehavior(GameContext context) throws GameException;

	/**
	 * Provide the target manager (how to target players)
	 * 
	 * @param context
	 *            the current context of the game (game, players, board). If
	 *            reload is disabled, then the context is never refreshed
	 * @return the target manager instance
	 * @throws GameException
	 *             when unexpected exception is raised
	 */
	protected abstract TargetBehavior getTargetBehavior(GameContext context) throws GameException;

	@Override
	public void run(GameContext context) throws GameException {
		if (reload || delegateEnemyBehavior == null) {
			delegateEnemyBehavior = new DelegateEnemyBehavior(getEnemy(), boardService, getMoveBehavior(context), getActionBehavior(context), getTargetBehavior(context));
		}
		delegateEnemyBehavior.run(context);
	}

}
