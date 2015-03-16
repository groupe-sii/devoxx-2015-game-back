package fr.sii.survival.core.service.game;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.AlreadyInGameException;
import fr.sii.survival.core.exception.FullGameException;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.exception.GameNotFoundException;
import fr.sii.survival.core.exception.PlayerNotFoundException;
import fr.sii.survival.core.listener.game.GameListenerRegistry;

public interface GameService extends GameListenerRegistry {

	/**
	 * Create a new game
	 * 
	 * @return the created game
	 */
	public Game create();

	/**
	 * Select a game. If needed, a new game can be created. According to
	 * implementation, the selection can be load balanced.
	 * 
	 * @return the selected game
	 */
	public Game select();

	/**
	 * Start the game
	 * 
	 * @param game
	 *            the game to start
	 * @throws GameException
	 *             when the game couldn't start or already started
	 */
	public void start(Game game) throws GameException;

	/**
	 * Stop the game
	 * 
	 * @param game
	 *            the game to stop
	 * @throws GameException
	 *             when the game couldn't stop or already stopped
	 */
	public void stop(Game game) throws GameException;

	/**
	 * Is the game started
	 * 
	 * @param game
	 *            the game to check if it is started
	 * @return true if the game is started, false otherwise
	 */
	public boolean isStarted(Game game);

	/**
	 * Add a player to the game. If the player can't be added because the game
	 * is already full, then a {@link FullGameException} is thrown. If a player
	 * with the same name already exists, then a {@link AlreadyInGameException}
	 * is thrown. The limitation of players is only applicable on real players
	 * but not on enemies
	 * 
	 * @param game
	 *            the game joined by the player
	 * @param player
	 *            the player to add to the game
	 * @throws FullGameException
	 *             when the game is full
	 * @throws AlreadyInGameException
	 *             when the player is already in the game
	 */
	public void join(Game game, Player player) throws GameException;

	/**
	 * Remove a player from the game.
	 * 
	 * @param game
	 *            the game leaved by the player
	 * @param player
	 *            the player to remove
	 * @throws GameException
	 *             when an error occurred while leaving the game
	 */
	public void quit(Game game, Player player) throws GameException;

	/**
	 * Get the player from its id
	 * 
	 * @param game
	 *            the game used to get player
	 * @param playerId
	 *            the id of the player
	 * @return the player if exists in the game
	 * @throws PlayerNotFoundException
	 *             when player doesn't exist in the game
	 */
	public Player getPlayer(Game game, String playerId) throws PlayerNotFoundException;

	/**
	 * Get the game instance from its id
	 * 
	 * @param gameId
	 *            the id of the game
	 * @return the game instance if exists
	 * @throws GameNotFoundException
	 *             when the provided id is null or when the game doesn't exist
	 */
	public Game getGame(String gameId) throws GameNotFoundException;
}
