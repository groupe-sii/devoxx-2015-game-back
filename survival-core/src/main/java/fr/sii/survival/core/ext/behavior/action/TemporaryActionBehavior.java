package fr.sii.survival.core.ext.behavior.action;

import java.util.concurrent.TimeUnit;


/**
 * Behavior that executes an action and execute its undo after a provided delay.
 * This is a specialization of {@link MultiActionBehavior}.
 * It uses a {@link DelayedActionBehavior} to wait for the provided delay before automatically executing the undo action.
 * 
 * @author aurelien
 *
 */
public class TemporaryActionBehavior extends MultiActionBehavior {

	public TemporaryActionBehavior(EnemyActionBehavior action, EnemyActionBehavior undo, long delay) {
		super(action, new DelayedActionBehavior(undo, delay));
	}
	
	public TemporaryActionBehavior(EnemyActionBehavior action, EnemyActionBehavior undo, int delay, TimeUnit unit) {
		this(action, undo, TimeUnit.MILLISECONDS.convert(delay, unit));
	}

}