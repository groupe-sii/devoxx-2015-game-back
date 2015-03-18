package fr.sii.survival.ext;

import fr.sii.survival.core.domain.image.ClientImage;
import fr.sii.survival.core.domain.player.Wizard;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.behavior.action.EnemyActionManager;
import fr.sii.survival.core.ext.behavior.action.FleeingEnemyManager;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveManager;
import fr.sii.survival.core.ext.behavior.move.RandomAroundNearManager;
import fr.sii.survival.core.ext.behavior.target.RandomPlayerTargetManager;
import fr.sii.survival.core.ext.behavior.target.TargetManager;

public class Lemming extends DelegateEnemyExtension {

	public Lemming() {
		super("Lemming", new ClientImage("lemming"), 10);
	}

	@Override
	protected EnemyActionManager getActionManager(GameContext context) {
		return new FleeingEnemyManager(actionService, getEnemy());
	}

	@Override
	protected EnemyMoveManager getMoveManager(GameContext context) {
		return new RandomAroundNearManager();
	}

	@Override
	protected TargetManager getTargetManager(GameContext context) {
		return new RandomPlayerTargetManager(Wizard.class);
	}

}
