package fr.sii.survival.core.service.player;

import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.player.PlayerListenerRegistry;

public interface PlayerService extends PlayerListenerRegistry {
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
	 * @return the new life value
	 */
	public int updateCurrentLife(Player player, int increment);

	/**
	 * Update the maximum life of the player. TODO: Limit max value range ???
	 * 
	 * @param player
	 *            the player to update maximum life
	 * @param increment
	 *            the increment to apply to the maximum life (positive or
	 *            negative)
	 * @return the new updated maximum life
	 */
	public int updateMaxLife(Player player, int increment);
}
