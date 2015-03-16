package fr.sii.survival.ext;

import java.util.concurrent.TimeUnit;

import fr.sii.survival.core.domain.player.Wizard;
import fr.sii.survival.core.ext.DelegateEnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.SpecialEnemy;
import fr.sii.survival.core.ext.behavior.action.AreaActionManager;
import fr.sii.survival.core.ext.behavior.action.AttackActionManager;
import fr.sii.survival.core.ext.behavior.action.DelayedActionManager;
import fr.sii.survival.core.ext.behavior.action.EnemyActionManager;
import fr.sii.survival.core.ext.behavior.action.shape.Circle;
import fr.sii.survival.core.ext.behavior.move.EnemyMoveManager;
import fr.sii.survival.core.ext.behavior.move.RandomAroundNearManager;
import fr.sii.survival.core.ext.behavior.target.RandomPlayerTargetManager;
import fr.sii.survival.core.ext.behavior.target.TargetManager;

public class WorldBoss extends DelegateEnemyExtension implements SpecialEnemy {

	public WorldBoss() {
		super("WorldBoss", "worldboss.png", Integer.MAX_VALUE);
	}

	@Override
	protected EnemyActionManager getActionManager(GameContext context) {
		return new DelayedActionManager(
				new AreaActionManager(
						new AttackActionManager(actionService, 500),
						new Circle(context.getBoard(), 3)),
				1, TimeUnit.SECONDS);
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
