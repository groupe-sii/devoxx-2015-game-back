package fr.sii.survival.core.service.action;

import java.util.List;

import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.listener.action.ActionListenerTrigger;
import fr.sii.survival.core.service.player.PlayerService;

/**
 * Action manager that is able to update states applied to player.
 * 
 * @author aurelien
 *
 */
public class ChangeStateActionManager implements ActionManager<ChangeStates> {

	private PlayerService playerService;

	private ActionListenerTrigger actionListenerTrigger;

	public ChangeStateActionManager(PlayerService playerService, ActionListenerTrigger actionListenerTrigger) {
		super();
		this.playerService = playerService;
		this.actionListenerTrigger = actionListenerTrigger;
	}

	@Override
	public boolean supports(Action action) {
		return action instanceof StateChange;
	}

	@Override
	public void execute(ChangeStates action) {
		List<StateChange> applied = playerService.updateStates(action.getPlayer(), action.getStateChanges());
		action.setStateChanges(applied);
		actionListenerTrigger.triggerStateChanged(action.getPlayer(), action);
	}

}
