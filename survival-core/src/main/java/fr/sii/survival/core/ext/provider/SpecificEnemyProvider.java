package fr.sii.survival.core.ext.provider;

import java.util.Arrays;
import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.exception.ExtensionInitializationException;
import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.extension.ExtensionService;

/**
 * Invoke a specific enemy every n enemies
 * 
 * @author aurelien
 *
 */
public class SpecificEnemyProvider extends AbstractProvider {

	/**
	 * The other provider
	 */
	private ExtensionProvider delegate;

	/**
	 * The number of enemies to wait before invoking special enemy
	 */
	private int every;

	/**
	 * The class of the enemy to invoke
	 */
	private Class<? extends EnemyExtension> special;

	private int count;

	public SpecificEnemyProvider(ActionService actionService, BoardService boardService, ExtensionService extensionService, int every, Class<? extends EnemyExtension> special, ExtensionProvider delegate) {
		super(actionService, boardService, extensionService);
		this.delegate = delegate;
		this.every = every;
		this.special = special;
		count = 0;
	}

	@Override
	public List<EnemyExtension> getEnemies(Game game) throws ExtensionInitializationException {
		if(count >= every) {
			count = 0;
			return Arrays.asList(instantiate(special));
		} else {
			return delegate.getEnemies(game);
		}
	}

}
