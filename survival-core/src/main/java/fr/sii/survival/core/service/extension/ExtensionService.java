package fr.sii.survival.core.service.extension;

import fr.sii.survival.core.domain.extension.Developer;
import fr.sii.survival.core.listener.action.ActionListener;
import fr.sii.survival.core.listener.player.PlayerListener;

public interface ExtensionService {
	/**
	 * Get the developer information from the action listener
	 * 
	 * @param listener
	 *            the action listener used for retrieving developer information
	 * @return the developer information if found, null otherwise
	 */
	public Developer getDeveloper(ActionListener listener);

	/**
	 * Get the developer information from the player listener
	 * 
	 * @param listener
	 *            the player listener used for retrieving developer information
	 * @return the developer information if found, null otherwise
	 */
	public Developer getDeveloper(PlayerListener listener);
}
