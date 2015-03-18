package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.service.action.ActionService;

public abstract class SimpleActionBehavior implements EnemyActionBehavior {

	protected ActionService actionService;
	
	protected Enemy enemy;
	
	public SimpleActionBehavior(ActionService actionService, Enemy enemy) {
		super();
		this.actionService = actionService;
		this.enemy = enemy;
	}
}
