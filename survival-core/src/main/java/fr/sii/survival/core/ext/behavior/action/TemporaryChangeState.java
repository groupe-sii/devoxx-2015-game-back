package fr.sii.survival.core.ext.behavior.action;

import java.util.concurrent.TimeUnit;

import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.action.StateChange.Change;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.service.action.ActionService;

/**
 * Behavior that adds a state to a player and remove it after a provided delay.
 * This is a specialization of {@link MultiActionBehavior} that delegates state changes to {@link ChangeStateActionBehavior}.
 * It also uses a {@link DelayedActionBehavior} to wait for the provided delay before automatically removing the state.
 * 
 * @author aurelien
 *
 */
public class TemporaryChangeState extends MultiActionBehavior {

	
	public TemporaryChangeState(ActionService actionService, Enemy enemy, String state, int delay, TimeUnit unit) {
		this(new ChangeStateActionBehavior(actionService, enemy, new StateChange(state, Change.ADD)), new DelayedActionBehavior(new ChangeStateActionBehavior(actionService, enemy, new StateChange(state, Change.REMOVE)), delay, unit));
	}
	
	protected TemporaryChangeState(EnemyActionBehavior addState, EnemyActionBehavior removeState) {
		super(addState, removeState);
	}

}
