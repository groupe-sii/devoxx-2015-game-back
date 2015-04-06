package fr.sii.survival.core.ext.provider;

import java.util.ArrayList;
import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.exception.ExtensionInitializationException;
import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.service.game.PlayerTypePredicate;

/**
 * Provider that limits the number of enemies on the field. If the maximum
 * number is set to 0 or less then this provider has no effect.
 * 
 * @author Aurélien Baudet
 *
 */
public class MaxProvider implements ExtensionProvider {

	/**
	 * The maximum number of enemies
	 */
	private int max;

	/**
	 * The delegate that will really provide enemies
	 */
	private ExtensionProvider delegate;

	public MaxProvider(int max, ExtensionProvider delegate) {
		super();
		this.max = max;
		this.delegate = delegate;
	}

	@Override
	public List<EnemyExtension> getEnemies(Game game) throws ExtensionInitializationException {
		if (max <= 0 || game.getPlayers(new PlayerTypePredicate(Enemy.class)).size() < max) {
			return delegate.getEnemies(game);
		} else {
			return new ArrayList<>(0);
		}
	}

}
