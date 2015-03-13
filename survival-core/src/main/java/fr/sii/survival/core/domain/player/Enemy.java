package fr.sii.survival.core.domain.player;

import fr.sii.survival.core.ext.EnemyExtension;

public interface Enemy extends Player {
	public EnemyExtension getExtension();
}
