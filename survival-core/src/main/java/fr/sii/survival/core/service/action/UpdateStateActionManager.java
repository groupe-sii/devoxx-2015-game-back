package fr.sii.survival.core.service.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.UpdateStates;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.action.ActionListenerTrigger;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.player.PlayerService;

/**
 * Action manager that is able to update states applied to player.
 * 
 * @author aurelien
 *
 */
public class UpdateStateActionManager implements ActionManager<UpdateStates> {
	private static final Logger LOG = LoggerFactory.getLogger(UpdateStateActionManager.class);

	private BoardService boardService;

	private PlayerService playerService;

	private ActionListenerTrigger actionListenerTrigger;

	public UpdateStateActionManager(BoardService boardService, PlayerService playerService, ActionListenerTrigger actionListenerTrigger) {
		super();
		this.boardService = boardService;
		this.playerService = playerService;
		this.actionListenerTrigger = actionListenerTrigger;
	}

	@Override
	public boolean supports(Action action) {
		return action instanceof UpdateStates;
	}

	@Override
	public void execute(Game game, Player p, UpdateStates action) throws GameException {
		LOG.debug("apply state changes {} on {}", action.getStateChanges(), action.getCell());
		List<Player> players = boardService.getPlayers(game.getBoard(), action.getCell());
		for(Player player : players) {
			List<StateChange> applied = playerService.updateStates(player, action.getStateChanges());
			if(!applied.isEmpty()) {
				actionListenerTrigger.triggerStateChanged(game, player, new UpdateStates(action.getCell(), applied));
			}
		}
	}

}
