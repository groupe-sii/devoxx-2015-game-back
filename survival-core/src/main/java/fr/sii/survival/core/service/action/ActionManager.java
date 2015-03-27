package fr.sii.survival.core.service.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.exception.GameException;

/**
 * Manager for action that is able to handle one {@link Action}
 * 
 * @author Aurélien Baudet
 *
 * @param <A>
 *            the type of the action that the action manager is able to handle
 */
public interface ActionManager<A extends Action> {
	/**
	 * Indicates if the action can be handled by the action manager
	 * 
	 * @param action
	 *            the action to check if able to manage it
	 * @return true if action can be handled, false otherwise
	 */
	public boolean supports(Action action);

	/**
	 * Method that really executes the action
	 * 
	 * @param game
	 *            the game to execute action on
	 * @param player
	 *            the player that initiated the action
	 * @param action
	 *            the action to execute
	 * @throws ActionException
	 *             when action could not be executed
	 * @throws GameException
	 *             when action couldn't be executed
	 */
	public void execute(Game game, Player player, A action) throws ActionException, GameException;
}
