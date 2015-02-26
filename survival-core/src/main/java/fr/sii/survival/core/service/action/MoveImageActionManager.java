package fr.sii.survival.core.service.action;

import java.util.List;

import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.action.ActionListenerTrigger;
import fr.sii.survival.core.service.board.BoardService;

/**
 * Action manager that is able to handle image moves.
 * 
 * @author aurelien
 *
 */
public class MoveImageActionManager implements ActionManager<MoveImage> {

	private BoardService boardService;
	
	private ActionListenerTrigger actionListenerTrigger;
	
	public MoveImageActionManager(BoardService boardService, ActionListenerTrigger actionListenerTrigger) {
		super();
		this.boardService = boardService;
		this.actionListenerTrigger = actionListenerTrigger;
	}

	@Override
	public boolean supports(Action action) {
		return action instanceof MoveImage;
	}

	@Override
	public void execute(MoveImage action) {
		List<Player> players = boardService.getPlayers(action.getStart());
		for(Player player : players) {
			boardService.move(player, action.getEnd());
			actionListenerTrigger.triggerImageMoved(action);
		}
	}

}
