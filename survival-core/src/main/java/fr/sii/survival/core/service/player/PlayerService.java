package fr.sii.survival.core.service.player;

import java.util.List;

import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.PlayerInfo;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.player.PlayerListenerRegistry;

public interface PlayerService extends PlayerListenerRegistry {
	/**
	 * Create a new player
	 * 
	 * @param info
	 *            the player information
	 * @return the player instance
	 */
	public Player create(PlayerInfo info);

	/**
	 * Update the life of the current player:
	 * <ul>
	 * <li>If the new computed life is under 0, then the player is dead</li>
	 * <li>If the new computed life is over max life, then player is fully
	 * healed and new life equals to max life</li>
	 * <li>If the player was dead (old life under 0) and now the new life is
	 * over 0, then the player is revived</li>
	 * </ul>
	 * 
	 * @param player
	 *            the player to update
	 * @param increment
	 *            the life increment to apply to the player (if positive, then
	 *            player is healed, if negative, then player is hit)
	 * @return the real applied increment
	 * @throws GameException
	 *             when the life couldn't be updated
	 */
	public int updateCurrentLife(Player player, int increment) throws GameException;

	/**
	 * Update the maximum life of the player. The maximum life must be between a
	 * minimum and a maximum value.
	 * 
	 * @param player
	 *            the player to update maximum life
	 * @param increment
	 *            the increment to apply to the maximum life (positive or
	 *            negative)
	 * @return the real applied increment
	 * @throws GameException
	 *             when the life couldn't be updated
	 */
	public int updateMaxLife(Player player, int increment) throws GameException;

	/**
	 * Update the states of the player
	 * 
	 * @param player
	 *            the player to update
	 * @param stateChanges
	 *            the list of state changes to apply
	 * @return the list of changes that were really applied
	 * @throws GameException
	 *             when the states couldn't be updated
	 */
	public List<StateChange> updateStates(Player player, List<StateChange> stateChanges) throws GameException;
}
