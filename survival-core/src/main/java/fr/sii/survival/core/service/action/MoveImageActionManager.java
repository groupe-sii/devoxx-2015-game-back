package fr.sii.survival.core.service.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.listener.action.ActionListenerTrigger;

/**
 * Action manager that is able to handle image moves.
 * 
 * @author aurelien
 *
 */
public class MoveImageActionManager implements ActionManager<MoveImage> {
	private static Logger logger = LoggerFactory.getLogger(MoveImageActionManager.class);

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
	public void execute(MoveImage action) {
		logger.info("move image from {} to {}", action.getStart(), action.getEnd());
		// nothing special to do, image move is just for client
		actionListenerTrigger.triggerImageMoved(action);
	}

}
