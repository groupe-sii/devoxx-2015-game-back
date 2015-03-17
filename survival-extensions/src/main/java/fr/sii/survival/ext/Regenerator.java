package fr.sii.survival.ext;

import fr.sii.survival.core.domain.image.ClientImage;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.behavior.action.EnemyActionManager;
import fr.sii.survival.core.ext.behavior.action.HealActionManager;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveManager;
import fr.sii.survival.core.ext.behavior.move.RandomAroundNearManager;
import fr.sii.survival.core.ext.behavior.target.SinglePlayerTargetManager;
import fr.sii.survival.core.ext.behavior.target.TargetManager;

public class Regenerator extends DelegateEnemyExtension {

	public Regenerator() {
		super("Regenerator", new ClientImage("regenerator"), 5000);
	}

	@Override
	protected EnemyActionManager getActionManager(GameContext context) {
		return new HealActionManager(actionService, 50);
	}

	@Override
	protected EnemyMoveManager getMoveManager(GameContext context) {
		return new RandomAroundNearManager();
	}

	@Override
	protected TargetManager getTargetManager(GameContext context) {
		return new SinglePlayerTargetManager(getEnemy());
	}

}
