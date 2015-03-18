package fr.sii.survival.core.service.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.RemoveImage;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.action.ActionListenerTrigger;

/**
 * Action manager that is able to handle remove of image.
 * 
 * @author aurelien
 *
 */
public class RemoveImageActionManager implements ActionManager<RemoveImage> {
	private static Logger logger = LoggerFactory.getLogger(RemoveImageActionManager.class);

	private ActionListenerTrigger actionListenerTrigger;

	public RemoveImageActionManager(ActionListenerTrigger actionListenerTrigger) {
		super();
		this.actionListenerTrigger = actionListenerTrigger;
	}

	@Override
	public boolean supports(Action action) {
		return action instanceof RemoveImage;
	}

	@Override
	public void execute(Game game, Player p, RemoveImage action) {
		logger.info("remove image from {}", action.getCell());
		// nothing special to do, image remove is just for client
		actionListenerTrigger.triggerImageRemoved(game, action);
	}

}
