package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.service.action.ActionService;

/**
 * Base class for action behaviors. It just forces to provide
 * {@link ActionService} and {@link Enemy} instances in order to be available in
 * sub-classes.
 * 
 * @author Aurélien Baudet
 *
 */
public abstract class SimpleActionBehavior implements EnemyActionBehavior {

	protected ActionService actionService;

	protected Enemy enemy;

	public SimpleActionBehavior(ActionService actionService, Enemy enemy) {
		super();
		this.actionService = actionService;
		this.enemy = enemy;
	}
}
