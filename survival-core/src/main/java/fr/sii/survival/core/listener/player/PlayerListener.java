package fr.sii.survival.core.listener.player;

import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;

/**
 * Listener that indicates changes applied on the player
 * 
 * @author Aurélien Baudet
 *
 */
public interface PlayerListener {
	/**
	 * Fired when the player current life is equal or under 0
	 * 
	 * @param game
	 *            the game where the player is currently in
	 * @param player
	 *            the player who die
	 */
	public void dead(Game game, Player player);

	/**
	 * Fired when the player comes back to life
	 * 
	 * @param game
	 *            the game where the player is currently in
	 * @param player
	 *            the player that comes back to life
	 */
	public void revived(Game game, Player player);

	/**
	 * Fired when the player life is decreased
	 * 
	 * @param game
	 *            the game where the player is currently in
	 * @param player
	 *            the hit player
	 * @param damage
	 *            the damage value (positive value)
	 */
	public void hit(Game game, Player player, int damage);

	/**
	 * Fired when the player life is increased
	 * 
	 * @param game
	 *            the game where the player is currently in
	 * @param player
	 *            the healed player
	 * @param amount
	 *            the amount of life (positive value)
	 */
	public void healed(Game game, Player player, int amount);

	/**
	 * Fired when the state of the player has changed
	 * 
	 * @param game
	 *            the game where the player is currently in
	 * @param player
	 *            the updated player
	 * @param changes
	 *            the list of changes that were applied
	 */
	public void statesChanged(Game game, Player player, List<StateChange> changes);

	/**
	 * Fired when the maximum life of the player has changed
	 * 
	 * @param game
	 *            the game where the player is currently in
	 * @param player
	 *            the updated player
	 * @param amount
	 *            the amount of life added (if positive) or removed (if
	 *            negative)
	 */
	public void maxLifeChanged(Game game, Player player, int amount);
}
