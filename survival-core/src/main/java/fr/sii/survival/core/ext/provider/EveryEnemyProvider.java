package fr.sii.survival.core.ext.provider;

import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.exception.ExtensionInitializationException;
import fr.sii.survival.core.ext.EnemyExtension;

/**
 * Delegate enemy invocation to principal provider until the number of enemies
 * has not reached the provided value. Delegate enemy invocation to alternative
 * when the number of enemies has reached the provided value.
 * 
 * @author Aurélien Baudet
 *
 */
public class EveryEnemyProvider implements ExtensionProvider {

	/**
	 * The principal provider that will be invoked until the number of enemies
	 * has not reached the provided every value
	 */
	private ExtensionProvider principal;

	/**
	 * The alternative provider that will be invoked when the number of enemies
	 * has reached the provided every value
	 */
	private ExtensionProvider alternative;

	/**
	 * The number of enemies to wait before invoking special enemy
	 */
	private int every;

	private int count;

	public EveryEnemyProvider(int every, ExtensionProvider principal, ExtensionProvider alternative) {
		this.principal = principal;
		this.every = every;
		this.alternative = alternative;
		count = 0;
	}

	@Override
	public List<EnemyExtension> getEnemies(Game game) throws ExtensionInitializationException {
		count++;
		if (count < every) {
			return principal.getEnemies(game);
		} else {
			count = 0;
			return alternative.getEnemies(game);
		}
	}

}
