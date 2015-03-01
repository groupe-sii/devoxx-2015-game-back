package fr.sii.survival.core.service.action;

import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.exception.ActionException;

/**
 * Manager for action that is able to handle one {@link Action}
 * 
 * @author aurelien
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
	 * @param action
	 *            the action to execute
	 * @throws ActionException
	 *             when action could not be executed
	 */
	public void execute(A action) throws ActionException;
}
