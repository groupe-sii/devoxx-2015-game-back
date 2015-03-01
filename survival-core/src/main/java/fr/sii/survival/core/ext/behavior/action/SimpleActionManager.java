package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.service.action.ActionService;

public abstract class SimpleActionManager implements EnemyActionManager {

	protected ActionService actionService;
	
	public SimpleActionManager(ActionService actionService) {
		super();
		this.actionService = actionService;
	}
}
