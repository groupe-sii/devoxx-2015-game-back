package fr.sii.survival.controller;

import static fr.sii.survival.config.PlayerConfiguration.PLAYER_PUBLISH_PREFIX;
import static fr.sii.survival.config.PlayerConfiguration.PLAYER_MAPPING_PREFIX;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;
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
	public void moveUp() {
		// get current player
		Player player = gameService.getPlayer(userContext.getPlayerId());
		// move the player
		boardService.move(player, Direction.UP);
	}

	@MessageMapping(PLAYER_MAPPING_PREFIX+"/move/down")
	public void moveDown() {
		// get current player
		Player player = gameService.getPlayer(userContext.getPlayerId());
		// move the player
		boardService.move(player, Direction.DOWN);
	}

	@MessageMapping(PLAYER_MAPPING_PREFIX+"/move/left")
	public void moveLeft() {
		// get current player
		Player player = gameService.getPlayer(userContext.getPlayerId());
		// move the player
		boardService.move(player, Direction.LEFT);
	}

	@MessageMapping(PLAYER_MAPPING_PREFIX+"/move/right")
	public void moveRight() {
		// get current player
		Player player = gameService.getPlayer(userContext.getPlayerId());
		// move the player
		boardService.move(player, Direction.RIGHT);
	}

	@Override
	public void dead(Player player) {
		template.convertAndSend(PLAYER_PUBLISH_PREFIX+"/died", player);
	}

	@Override
	public void revived(Player player) {
		template.convertAndSend(PLAYER_PUBLISH_PREFIX+"/revived", player);
	}

	@Override
	public void hit(Player player, int damage) {
		template.convertAndSend(PLAYER_PUBLISH_PREFIX+"/hit", new PlayerLifeUpdate(player, damage));
	}

	@Override
	public void healed(Player player, int amount) {
		template.convertAndSend(PLAYER_PUBLISH_PREFIX+"/healed", new PlayerLifeUpdate(player, amount));
	}

	@Override
	public void statesChanged(Player player, List<StateChange> changes) {
		template.convertAndSend(PLAYER_PUBLISH_PREFIX+"/states", new PlayerStateUpdate(player, changes));
	}

	@Override
	public void maxLifeChanged(Player player, int amount) {
		template.convertAndSend(PLAYER_PUBLISH_PREFIX+"/max", new PlayerLifeUpdate(player, amount));
	}

}
