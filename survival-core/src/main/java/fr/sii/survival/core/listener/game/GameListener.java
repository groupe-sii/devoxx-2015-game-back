package fr.sii.survival.core.listener.game;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;

public interface GameListener {
	/**
	 * Fired when the game has started
	 * 
	 * @param game
	 *            the started game
	 */
	public void started(Game game);

	/**
	 * Fired when the game has stopped
	 * 
	 * @param game
	 *            the stopped game
	 */
	public void stopped(Game game);

	/**
	 * Fired when a player has joined the game
	 * 
	 * @param player
	 *            the player who joined
	 * @param game
	 *            the game information
	 */
	public void joined(Player player, Game game);

	/**
	 * Fired when a player has leaved the game
	 * 
	 * @param player
	 *            the player who joined
	 * @param game
	 *            the game information
	 */
	public void leaved(Player player, Game game);
}
