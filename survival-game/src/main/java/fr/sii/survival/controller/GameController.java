package fr.sii.survival.controller;

import static fr.sii.survival.WebSocketConfig.SERVER_PUBLISH_PREFIX;
import static fr.sii.survival.config.GameConfiguration.GAME_MAPPING_PREFIX;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.PlayerInfo;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.game.GameListener;
import fr.sii.survival.core.service.game.GameService;
import fr.sii.survival.core.service.player.PlayerService;
import fr.sii.survival.dto.PlayerAndGame;
import fr.sii.survival.session.UserContext;

@Controller
public class GameController extends ErrorController implements GameListener {

	private static Logger logger = LoggerFactory.getLogger(GameController.class);

	@Autowired
	SimpMessagingTemplate template;

	@Autowired
	GameService gameService;

	@Autowired
	PlayerService playerService;

	@Autowired
	UserContext userContext;
	
	@MessageMapping(GAME_MAPPING_PREFIX+"/create")
	@SendTo(SERVER_PUBLISH_PREFIX+"/created")
	public Game create() {
		return gameService.create();
	}

	@MessageMapping(GAME_MAPPING_PREFIX+"/${gameId}/join")
	public void join(@DestinationVariable String gameId, PlayerInfo player) throws GameException {
		logger.info("player {} is joining the game {}", player, gameId);
		Player p = playerService.create(player);
		Game game = gameService.getGame(gameId);
		gameService.join(game, p);
		// update the context of the connected user
		userContext.setPlayerId(p.getId());
		userContext.setGameId(gameId);
	}

	@MessageMapping(GAME_MAPPING_PREFIX+"/${gameId}/leave")
	public void quit(@DestinationVariable String gameId) throws GameException {
		Game game = gameService.getGame(gameId);
		Player player = gameService.getPlayer(game, userContext.getPlayerId());
		logger.info("player {} is quitting the game {}", player, game);
		gameService.quit(game, player);
		userContext.setGameId(null);
	}
	
	@Override
	public void started(Game game) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/started", game);
	}

	@Override
	public void stopped(Game game) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/stopped", game);
	}

	@Override
	public void joined(Player player, Game game) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/joined", new PlayerAndGame(player, game));
	}

	@Override
	public void leaved(Player player, Game game) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/leaved", new PlayerAndGame(player, game));
	}
}
