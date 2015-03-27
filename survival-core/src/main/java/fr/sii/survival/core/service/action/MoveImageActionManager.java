package fr.sii.survival.core.service.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.action.ActionListenerTrigger;

/**
 * Action manager that is able to handle image moves.
 * 
 * @author Aurélien Baudet
 *
 */
public class MoveImageActionManager implements ActionManager<MoveImage> {
	private static final Logger LOG = LoggerFactory.getLogger(MoveImageActionManager.class);

	private ActionListenerTrigger actionListenerTrigger;

	public MoveImageActionManager(ActionListenerTrigger actionListenerTrigger) {
		super();
		this.actionListenerTrigger = actionListenerTrigger;
	}

	@Override
	public boolean supports(Action action) {
		return action instanceof MoveImage;
	}

	@Override
	public void execute(Game game, Player p, MoveImage action) {
		LOG.debug("move image from {} to {}", action.getStart(), action.getEnd());
		// nothing special to do, image move is just for client
		actionListenerTrigger.triggerImageMoved(game, action);
	}

}
