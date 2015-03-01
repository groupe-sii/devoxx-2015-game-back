package fr.sii.survival.core.listener.player;

import java.util.List;

import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;

/**
 * Listener that indicates changes applied on the player
 * 
 * @author aurelien
 *
 */
public interface PlayerListener {
	/**
	 * Fired when the player current life is equal or under 0
	 * 
	 * @param player
	 *            the player who die
	 */
	public void dead(Player player);

	/**
	 * Fired when the player comes back to life
	 * 
	 * @param player
	 *            the player that comes back to life
	 */
	public void revived(Player player);

	/**
	 * Fired when the player life is decreased
	 * 
	 * @param player
	 *            the hit player
	 * @param damage
	 *            the damage value (positive value)
	 */
	public void hit(Player player, int damage);

	/**
	 * Fired when the player life is increased
	 * 
	 * @param player
	 *            the healed player
	 * @param amount
	 *            the amount of life (positive value)
	 */
	public void healed(Player player, int amount);

	/**
	 * Fired when the state of the player has changed
	 * 
	 * @param player
	 *            the updated player
	 * @param changes
	 *            the list of changes that were applied
	 */
	public void statesChanged(Player player, List<StateChange> changes);
}
