package fr.sii.survival.core.ext.provider;

import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.exception.ExtensionInitializationException;
import fr.sii.survival.core.ext.EnemyExtension;

/**
 * Provide a list of enemies to add into the game
 * 
 * @author Aurélien Baudet
 *
 */
public interface ExtensionProvider {
	/**
	 * Provide a list of enemies to add into the game. The provided enemies can
	 * be empty.
	 * 
	 * @param game
	 *            the current game
	 * @return the list of enemies
	 * @throws ExtensionInitializationException
	 *             when enemy couldn't be created
	 */
	public List<EnemyExtension> getEnemies(Game game) throws ExtensionInitializationException;
}
