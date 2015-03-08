package fr.sii.survival.controller;

import static fr.sii.survival.WebSocketConfig.SERVER_PUBLISH_PREFIX;
import static fr.sii.survival.config.PlayerConfiguration.PLAYER_MAPPING_PREFIX;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.player.PlayerListener;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.board.Direction;
import fr.sii.survival.core.service.game.GameService;
import fr.sii.survival.dto.PlayerLifeUpdate;
import fr.sii.survival.dto.PlayerStateUpdate;
import fr.sii.survival.session.UserContext;

@Controller
public class PlayerController extends ErrorController implements PlayerListener {
	@Autowired
	SimpMessagingTemplate template;

	@Autowired
	UserContext userContext;
	
	@Autowired
	GameService gameService;
	
	@Autowired
	BoardService boardService;
	
	@MessageMapping(PLAYER_MAPPING_PREFIX+"/move/up")
	public void moveUp() throws GameException {
		move(Direction.UP);
	}

	@MessageMapping(PLAYER_MAPPING_PREFIX+"/move/down")
	public void moveDown() throws GameException {
		move(Direction.DOWN);
	}

	@MessageMapping(PLAYER_MAPPING_PREFIX+"/move/left")
	public void moveLeft() throws GameException {
		move(Direction.LEFT);
	}

	@MessageMapping(PLAYER_MAPPING_PREFIX+"/move/right")
	public void moveRight() throws GameException {
		move(Direction.RIGHT);
	}

	@Override
	public void dead(Game game, Player player) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/player/died", player);
	}

	@Override
	public void revived(Game game, Player player) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/player/revived", player);
	}

	@Override
	public void hit(Game game, Player player, int damage) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/player/hit", new PlayerLifeUpdate(player, damage));
	}

	@Override
	public void healed(Game game, Player player, int amount) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/player/healed", new PlayerLifeUpdate(player, amount));
	}

	@Override
	public void statesChanged(Game game, Player player, List<StateChange> changes) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/player/states", new PlayerStateUpdate(player, changes));
	}

	@Override
	public void maxLifeChanged(Game game, Player player, int amount) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/player/life/max", new PlayerLifeUpdate(player, amount));
	}

	private void move(Direction direction) throws GameException {
		Game game = gameService.getGame(userContext.getGameId());
		// get current player
		Player player = gameService.getPlayer(game, userContext.getPlayerId());
		// move the player
		boardService.move(game.getBoard(), player, direction);
	}
}
