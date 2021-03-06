package fr.sii.survival.core.service.action;

import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.exception.ActionExecutionException;
import fr.sii.survival.core.exception.ActionManagerNotFoundException;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.action.ActionListener;
import fr.sii.survival.core.listener.action.ActionListenerRegistry;

/**
 * Action service that is able to register listeners and just delegates the
 * action execution to action managers.
 * 
 * @author Aurélien Baudet
 *
 */
public class DelegateActionService implements ActionService {

	/**
	 * The registry that stores the list of action listeners that will be
	 * triggered when an action is done
	 */
	private ActionListenerRegistry listenerRegistry;

	/**
	 * The list of managers that will be able to handle the actions
	 */
	private List<ActionManager<Action>> actionManagers;

	public DelegateActionService(ActionListenerRegistry listenerRegistry, List<ActionManager<Action>> actionManagers) {
		super();
		this.listenerRegistry = listenerRegistry;
		this.actionManagers = actionManagers;
	}

	@Override
	public void execute(Game game, Player player, Action action) throws ActionException {
		for (ActionManager<Action> actionManager : actionManagers) {
			if (actionManager.supports(action)) {
				try {
					actionManager.execute(game, player, action);
				} catch (GameException e) {
					throw new ActionExecutionException("Failed to execute action "+action, action, e);
				}
				return;
			}
		}
		throw new ActionManagerNotFoundException("No action manager available to handle provided action", action);
	}

	@Override
	public void addActionListener(ActionListener listener) {
		listenerRegistry.addActionListener(listener);
	}

	@Override
	public void removeActionListener(ActionListener listener) {
		listenerRegistry.removeActionListener(listener);
	}

}
