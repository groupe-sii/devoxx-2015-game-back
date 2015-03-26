package fr.sii.survival.core.ext.behavior.action;

import java.util.concurrent.TimeUnit;

import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.action.StateChange.Change;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.service.action.ActionService;

/**
 * Behavior that adds a state to a player and remove it after a provided delay.
 * This is a specialization of {@link MultiActionBehavior} that delegates state changes to {@link UpdateStateActionBehavior}.
 * It also uses a {@link DelayedActionBehavior} to wait for the provided delay before automatically removing the state.
 * 
 * @author aurelien
 *
 */
public class TemporaryChangeState extends TemporaryActionBehavior {

	public TemporaryChangeState(ActionService actionService, Enemy enemy, String state, long delay) {
		super(
				new UpdateStateActionBehavior(actionService, enemy, new StateChange(state, Change.ADD)),
				new UpdateStateActionBehavior(actionService, enemy, new StateChange(state, Change.REMOVE)),
				delay);
	}
	
	public TemporaryChangeState(ActionService actionService, Enemy enemy, String state, int delay, TimeUnit unit) {
		this(actionService, enemy, state, TimeUnit.MILLISECONDS.convert(delay, unit));
	}

}
