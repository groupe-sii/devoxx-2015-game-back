package fr.sii.survival.core.ext;

import fr.sii.survival.core.exception.GameException;


public interface EnemyExtension {
	public void init();
	
	public void run(GameContext context) throws GameException;
}
