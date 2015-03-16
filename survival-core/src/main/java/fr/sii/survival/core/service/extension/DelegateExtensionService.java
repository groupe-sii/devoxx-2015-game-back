package fr.sii.survival.core.service.extension;

import fr.sii.survival.core.domain.extension.Developer;
import fr.sii.survival.core.ext.DeveloperProvider;
import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.listener.action.ActionListener;
import fr.sii.survival.core.listener.board.BoardListener;
import fr.sii.survival.core.listener.game.GameListener;
import fr.sii.survival.core.listener.player.PlayerListener;

/**
 * Simple service that just delegates developer providing to specialized
 * implementation
 * 
 * @author aurelien
 *
 */
public class DelegateExtensionService implements ExtensionService {

	/**
	 * The developer provider
	 */
	private DeveloperProvider developerProvider;
	
	public DelegateExtensionService(DeveloperProvider developerProvider) {
		super();
		this.developerProvider = developerProvider;
	}

	@Override
	public Developer getDeveloper(ActionListener listener) {
		return developerProvider.getDeveloper(listener);
	}

	@Override
	public Developer getDeveloper(PlayerListener listener) {
		return developerProvider.getDeveloper(listener);
	}

	@Override
	public Developer getDeveloper(Class<? extends EnemyExtension> type) {
		return developerProvider.getDeveloper(type);
	}

	@Override
	public Developer getDeveloper(BoardListener listener) {
		return developerProvider.getDeveloper(listener);
	}

	@Override
	public Developer getDeveloper(GameListener listener) {
		return developerProvider.getDeveloper(listener);
	}

}
