package fr.sii.survival.core.listener.action;

import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;

/**
 * Listener that notifies when an action has been executed
 * 
 * @author aurelien
 *
 */
public interface ActionListener {
	/**
	 * Fired when the life of a player has changed
	 * 
	 * @param player
	 *            the player who life has changed
	 * @param action
	 *            the action that has been executed
	 */
	public void lifeUpdated(Player player, UpdateLife action);

	/**
	 * Fired when a player position has changed
	 * 
	 * @param player
	 *            the player who moved
	 * @param action
	 *            the action that has been executed
	 */
	public void positionChanged(Player player, ChangePosition action);

	/**
	 * Fired when an image has been moved
	 * 
	 * @param action
	 *            the action that has been executed
	 */
	public void imageMoved(MoveImage action);

	/**
	 * Fired when the states of a player has changed
	 * 
	 * @param player
	 *            the player who states changed
	 * @param action
	 *            the action that has been executed
	 */
	public void stateChanged(Player player, ChangeStates action);
}
