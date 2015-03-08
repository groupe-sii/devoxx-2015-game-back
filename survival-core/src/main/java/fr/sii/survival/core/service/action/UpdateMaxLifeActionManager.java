package fr.sii.survival.core.service.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.UpdateMaxLife;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.GameException;
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
	private static Logger logger = LoggerFactory.getLogger(UpdateMaxLifeActionManager.class);

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
	public void execute(Game game, UpdateMaxLife action) throws GameException {
		logger.info("update maximum life {} on {}", action.getIncrement(), action.getCell());
		List<Player> players = boardService.getPlayers(game.getBoard(), action.getCell());
		for(Player player : players) {
			int inc = playerService.updateMaxLife(player, action.getIncrement());
			actionListenerTrigger.triggerLifeUpdated(game, player, new UpdateMaxLife(inc, action.getCell()));
		}
	}

}
