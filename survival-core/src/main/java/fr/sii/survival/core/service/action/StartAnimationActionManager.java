package fr.sii.survival.core.service.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.StartAnimation;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.action.ActionListenerTrigger;

/**
 * Action manager that is able to handle animation starts.
 * 
 * @author aurelien
 *
 */
public class StartAnimationActionManager implements ActionManager<StartAnimation> {
	private static final Logger logger = LoggerFactory.getLogger(StartAnimationActionManager.class);

	private ActionListenerTrigger actionListenerTrigger;

	public StartAnimationActionManager(ActionListenerTrigger actionListenerTrigger) {
		super();
		this.actionListenerTrigger = actionListenerTrigger;
	}

	@Override
	public boolean supports(Action action) {
		return action instanceof StartAnimation;
	}

	@Override
	public void execute(Game game, Player p, StartAnimation action) {
		logger.debug("start animation {}", action.getName());
		// nothing special to do, image move is just for client
		actionListenerTrigger.triggerAnimationStarted(game, action);
	}

}
