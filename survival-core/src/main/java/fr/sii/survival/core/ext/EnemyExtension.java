package fr.sii.survival.core.ext;

import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.service.action.ActionService;


public abstract class EnemyExtension {
	protected ActionService actionService;
	
	public abstract void run(GameContext context) throws GameException;
	
	public ActionService getActionService() {
		return actionService;
	}

	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}
}
