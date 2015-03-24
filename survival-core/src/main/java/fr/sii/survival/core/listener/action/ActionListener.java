package fr.sii.survival.core.listener.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.AddImage;
import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.RemoveImage;
import fr.sii.survival.core.domain.action.StartAnimation;
import fr.sii.survival.core.domain.action.StopAnimation;
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
	 * @param game
	 *            the game in which the action was executed
	 * @param player
	 *            the player who life has changed
	 * @param action
	 *            the action that has been executed
	 */
	public void lifeUpdated(Game game, Player player, UpdateLife action);

	/**
	 * Fired when a player position has changed
	 * 
	 * @param game
	 *            the game in which the action was executed
	 * @param player
	 *            the player who moved
	 * @param action
	 *            the action that has been executed
	 */
	public void positionChanged(Game game, Player player, ChangePosition action);

	/**
	 * Fired when an image has been added
	 * 
	 * @param game
	 *            the game in which the action was executed
	 * @param action
	 *            the action that has been executed
	 */
	public void imageAdded(Game game, AddImage action);

	/**
	 * Fired when an image has been moved
	 * 
	 * @param game
	 *            the game in which the action was executed
	 * @param action
	 *            the action that has been executed
	 */
	public void imageMoved(Game game, MoveImage action);

	/**
	 * Fired when an image has been removed
	 * 
	 * @param game
	 *            the game in which the action was executed
	 * @param action
	 *            the action that has been executed
	 */
	public void imageRemoved(Game game, RemoveImage action);

	/**
	 * Fired when an animation has started
	 * 
	 * @param game
	 *            the game in which the action was executed
	 * @param action
	 *            the action that has been executed
	 */
	public void animationStarted(Game game, StartAnimation action);

	/**
	 * Fired when an animation has stopped
	 * 
	 * @param game
	 *            the game in which the action was executed
	 * @param action
	 *            the action that has been executed
	 */
	public void animationStopped(Game game, StopAnimation action);

	/**
	 * Fired when the states of a player has changed
	 * 
	 * @param game
	 *            the game in which the action was executed
	 * @param player
	 *            the player who states changed
	 * @param action
	 *            the action that has been executed
	 */
	public void stateChanged(Game game, Player player, ChangeStates action);
}
