package fr.sii.survival.core.ext;

import fr.sii.survival.core.domain.extension.Developer;
import fr.sii.survival.core.listener.action.ActionListener;
import fr.sii.survival.core.listener.board.BoardListener;
import fr.sii.survival.core.listener.game.GameListener;
import fr.sii.survival.core.listener.player.PlayerListener;

public interface DeveloperProvider {
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

	/**
	 * Get the developer information from the extension class
	 * 
	 * @param type
	 *            the class of the extension
	 * @return the associated developer if found, null otherwise
	 */
	public Developer getDeveloper(Class<? extends EnemyExtension> type);

	/**
	 * Get the developer information from the board listener
	 * 
	 * @param listener
	 *            the board listener used for retrieving developer information
	 * @return the developer information if found, null otherwise
	 */
	public Developer getDeveloper(BoardListener listener);
	
	/**
	 * Get the developer information from the game listener
	 * 
	 * @param listener
	 *            the game listener used for retrieving developer information
	 * @return the developer information if found, null otherwise
	 */
	public Developer getDeveloper(GameListener listener);
}
