package fr.sii.survival.core.service.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.action.ActionListenerTrigger;
import fr.sii.survival.core.service.board.BoardService;

/**
 * Action manager that is able to handle player moves.
 * 
 * @author aurelien
 *
 */
public class ChangePositionActionManager implements ActionManager<ChangePosition> {
	private static Logger logger = LoggerFactory.getLogger(ChangePositionActionManager.class);

	private BoardService boardService;
	
	private ActionListenerTrigger actionListenerTrigger;
	
	public ChangePositionActionManager(BoardService boardService, ActionListenerTrigger actionListenerTrigger) {
		super();
		this.boardService = boardService;
		this.actionListenerTrigger = actionListenerTrigger;
	}

	@Override
	public boolean supports(Action action) {
		return action instanceof ChangePosition;
	}

	@Override
	public void execute(ChangePosition action) {
		logger.info("change position of players from {} to {}", action.getStart(), action.getEnd());
		List<Player> players = boardService.getPlayers(action.getStart());
		for(Player player : players) {
			boardService.move(player, action.getEnd());
			actionListenerTrigger.triggerPositionChanged(player, action);
		}
	}

}
