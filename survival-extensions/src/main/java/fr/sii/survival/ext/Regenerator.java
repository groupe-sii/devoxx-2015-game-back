package fr.sii.survival.ext;

import fr.sii.survival.core.domain.image.ClientImage;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.behavior.action.EnemyActionBehavior;
import fr.sii.survival.core.ext.behavior.action.HealActionBehavior;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveBehavior;
import fr.sii.survival.core.ext.behavior.move.RandomMoveNearBehavior;
import fr.sii.survival.core.ext.behavior.target.SinglePlayerTargetBehavior;
import fr.sii.survival.core.ext.behavior.target.TargetBehavior;

public class Regenerator extends DelegateEnemyExtension {

	public Regenerator() {
		super("Regenerator", new ClientImage("regenerator"), 5000);
	}

	@Override
	protected EnemyActionBehavior getActionBehavior(GameContext context) {
		return new HealActionBehavior(actionService, getEnemy(), 50);
	}

	@Override
	protected EnemyMoveBehavior getMoveBehavior(GameContext context) {
		return new RandomMoveNearBehavior();
	}

	@Override
	protected TargetBehavior getTargetBehavior(GameContext context) {
		return new SinglePlayerTargetBehavior(getEnemy());
	}

}
