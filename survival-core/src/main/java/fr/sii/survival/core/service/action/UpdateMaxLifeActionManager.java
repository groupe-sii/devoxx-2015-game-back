package fr.sii.survival.core.service.action;

import java.util.List;

import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.UpdateMaxLife;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.action.ActionListenerTrigger;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.player.PlayerService;

/**
 * Action manager that is able to handle max life update.
 * 
 * @author aurelien
 *
 */
public class UpdateMaxLifeActionManager implements ActionManager<UpdateMaxLife> {

	private BoardService boardService;
	
	private PlayerService playerService;
	
	private ActionListenerTrigger actionListenerTrigger;
	
	public UpdateMaxLifeActionManager(BoardService boardService, PlayerService playerService, ActionListenerTrigger actionListenerTrigger) {
		super();
		this.boardService = boardService;
		this.playerService = playerService;
		this.actionListenerTrigger = actionListenerTrigger;
	}

	@Override
	public boolean supports(Action action) {
		return action instanceof UpdateMaxLife;
	}

	@Override
	public void execute(UpdateMaxLife action) {
		List<Player> players = boardService.getPlayers(action.getCell());
		for(Player player : players) {
			playerService.updateMaxLife(player, action.getIncrement());
			actionListenerTrigger.triggerLifeUpdated(player, action);
		}
	}

}
