package fr.sii.survival.core.service.game;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.AlreadyInGameException;
import fr.sii.survival.core.exception.FullGameException;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.game.GameListenerRegistry;

public interface GameService extends GameListenerRegistry {

	/**
	 * Start the game
	 * 
	 * @throws GameException
	 *             when the game couldn't start or already started
	 */
	public void start() throws GameException;

	/**
	 * End the game
	 * 
	 * @throws GameException
	 *             when the game couldn't stop or already stopped
	 */
	public void stop() throws GameException;

	/**
	 * Is the game started
	 * 
	 * @return true if the game is started, false otherwise
	 */
	public boolean isStarted();
	
	/**
	 * Add a player to the game. If the player can't be added because the game
	 * is already full, then a {@link FullGameException} is thrown. If a player
	 * with the same name already exists, then a {@link AlreadyInGameException}
	 * is thrown. The limitation of players is only applicable on real players
	 * but not on enemies
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

	/**
	 * Get the game information
	 * 
	 * @return the game information
	 */
	public Game getGame();
}
