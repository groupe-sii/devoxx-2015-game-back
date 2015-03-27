package fr.sii.survival.core.service.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.StopAnimation;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.action.ActionListenerTrigger;

/**
 * Action manager that is able to handle animation starts.
 * 
 * @author Aurélien Baudet
 *
 */
public class StopAnimationActionManager implements ActionManager<StopAnimation> {
	private static final Logger LOG = LoggerFactory.getLogger(StopAnimationActionManager.class);

	private ActionListenerTrigger actionListenerTrigger;

	public StopAnimationActionManager(ActionListenerTrigger actionListenerTrigger) {
		super();
		this.actionListenerTrigger = actionListenerTrigger;
	}

	@Override
	public boolean supports(Action action) {
		return action instanceof StopAnimation;
	}

	@Override
	public void execute(Game game, Player p, StopAnimation action) {
		LOG.debug("stop animation {}", action.getName());
		// nothing special to do, image move is just for client
		actionListenerTrigger.triggerAnimationStopped(game, action);
	}

}
