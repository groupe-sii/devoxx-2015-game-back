package fr.sii.survival.ext;

import fr.sii.survival.core.domain.image.ClientImage;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.behavior.action.ChangeStateActionBehavior;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.move.RandomAroundNearBehavior;
import fr.sii.survival.core.ext.behavior.target.SinglePlayerTargetBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;
import fr.sii.survival.ext.constants.States;

public class Immobilizator extends DelegateEnemyExtension {

	public Immobilizator() {
		super("Immobilizator", new ClientImage("immobilizator"), 5000);
	}

	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) {
		return new ChangeStateActionBehavior(actionService, enemy, States.IMMOBILIZED.toString(), true);
	}

	@Override
	protected EnemyMoveBehavior getMoveBehavior(GameContext context) {
		return new RandomAroundNearBehavior();
	}

	@Override
	protected TargetBehavior getTargetBehavior(GameContext context) {
		return new SinglePlayerTargetBehavior(getEnemy());
	}

}
