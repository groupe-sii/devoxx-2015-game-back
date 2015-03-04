package fr.sii.survival.core.service.game;

import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.AlreadyInGameException;
import fr.sii.survival.core.exception.FullGameException;
import fr.sii.survival.core.exception.GameException;

public interface GameService {

	/**
	 * Start the game
	 */
	public void start();

	/**
	 * End the game
	 */
	public void stop();

	/**
	 * Add a player to the game. If the player can't be added because the game
	 * is already full, then a {@link FullGameException} is thrown. If a player
	 * with the same name already exists, then a {@link AlreadyInGameException}
	 * is thrown.
	 * 
	 * @param player
	 *            the player to add to the game
	 * @throws FullGameException
	 *             when the game is full
	 * @throws AlreadyInGameException
	 *             when the player is already in the game
	 */
	public void join(Player player) throws GameException;

	/**
	 * Remove a player from the game.
	 * 
	 * @param player
	 *            the player to remove
	 */
	public void quit(Player player);

	/**
	 * Get the player from its id
	 * 
	 * @param playerId
	 *            the id of the player
	 * @return the player if exists in the game, null otherwise
	 */
	public Player getPlayer(String playerId);

}
