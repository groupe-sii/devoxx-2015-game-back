package fr.sii.survival.core.service.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.listener.action.ActionListenerRegistry;

public interface ActionService extends ActionListenerRegistry {
	/**
	 * Execute the provided action on the provided game
	 * 
	 * @param game
	 *            the game to execute action on
	 * @param action
	 *            the action to execute
	 * @throws ActionException
	 *             when the action couldn't be executed
	 */
	public void execute(Game game, Action action) throws ActionException;
}
