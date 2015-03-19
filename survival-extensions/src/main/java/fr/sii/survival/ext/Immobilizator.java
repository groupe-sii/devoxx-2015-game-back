package fr.sii.survival.ext;

import java.util.concurrent.TimeUnit;

import fr.sii.survival.core.domain.image.ClientImage;
import fr.sii.survival.core.domain.player.Wizard;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.action.TemporaryChangeState;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.move.RandomMoveNearBehavior;
import fr.sii.survival.core.ext.behavior.target.RandomPlayerTargetBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;
import fr.sii.survival.ext.constants.States;

public class Immobilizator extends DelegateEnemyExtension {

	public Immobilizator() {
		super("Immobilizator", new ClientImage("immobilizator"), 5000);
	}

	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) {
		return new TemporaryChangeState(actionService, enemy, States.IMMOBILIZED.toString(), 5, TimeUnit.SECONDS);
	}

	@Override
	protected EnemyMoveBehavior getMoveBehavior(GameContext context) {
		return new RandomMoveNearBehavior();
	}

	@Override
	protected TargetBehavior getTargetBehavior(GameContext context) {
		return new RandomPlayerTargetBehavior(Wizard.class);
	}

}
