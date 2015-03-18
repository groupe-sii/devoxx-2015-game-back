package fr.sii.survival.core.service.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.listener.action.ActionListenerRegistry;

public interface ActionService extends ActionListenerRegistry {
	/**
	 * Execute the provided action on the provided game
	 * 
	 * @param game
	 *            the game to execute action on
	 * @param player
	 *            the player that initiated the action
	 * @param action
	 *            the action to execute
	 * @throws ActionException
	 *             when the action couldn't be executed
	 */
	public void execute(Game game, Player player, Action action) throws ActionException;
}
