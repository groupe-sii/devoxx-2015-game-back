package fr.sii.survival.core.service.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.AddImage;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.action.ActionListenerTrigger;

/**
 * Action manager that is able to handle image adds.
 * 
 * @author aurelien
 *
 */
public class AddImageActionManager implements ActionManager<AddImage> {
	private static Logger logger = LoggerFactory.getLogger(AddImageActionManager.class);

	private ActionListenerTrigger actionListenerTrigger;

	public AddImageActionManager(ActionListenerTrigger actionListenerTrigger) {
		super();
		this.actionListenerTrigger = actionListenerTrigger;
	}

	@Override
	public boolean supports(Action action) {
		return action instanceof AddImage;
	}

	@Override
	public void execute(Game game, Player p, AddImage action) {
		logger.info("add image to {}", action.getCell());
		// nothing special to do, image add is just for client
		actionListenerTrigger.triggerImageAdded(game, action);
	}

}
