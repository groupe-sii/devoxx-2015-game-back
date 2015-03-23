package fr.sii.survival.controller;

import static fr.sii.survival.WebSocketConfig.SERVER_PUBLISH_PREFIX;
import static fr.sii.survival.config.GameConfiguration.GAME_MAPPING_PREFIX;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import fr.sii.survival.config.options.GameOptions;
import fr.sii.survival.config.options.LifeOptions;
import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.PlayerInfo;
import fr.sii.survival.core.domain.player.Wizard;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.exception.GameNotFoundException;
import fr.sii.survival.core.listener.game.GameListener;
import fr.sii.survival.core.service.game.GameService;
import fr.sii.survival.core.service.player.PlayerService;
import fr.sii.survival.session.UserContext;

@Controller
public class GameController extends ErrorController implements GameListener {

	private static final Logger logger = LoggerFactory.getLogger(GameController.class);

	@Autowired
	SimpMessagingTemplate template;

	@Autowired
	GameService gameService;

	@Autowired
	PlayerService playerService;

	@Autowired
	LifeOptions lifeOptions;

	@Autowired
	UserContext userContext;
	
	@Autowired
	GameOptions gameOptions;
	
	@MessageMapping(GAME_MAPPING_PREFIX+"/create")
	@SendToUser(SERVER_PUBLISH_PREFIX+"/created")
	public Game create() {
		return gameService.create();
	}

	@MessageMapping(GAME_MAPPING_PREFIX+"/select")
	@SendToUser(SERVER_PUBLISH_PREFIX+"/selected")
	public Game select() {
		logger.debug("selecting game");
		Game game = gameService.select();
		logger.info("game selected {}", game);
		return game;
	}
	
	@MessageMapping(GAME_MAPPING_PREFIX+"/{gameId}/info")
	@SendToUser(SERVER_PUBLISH_PREFIX+"/info")
	public Game info(@DestinationVariable String gameId) throws GameNotFoundException {
		return gameService.getGame(gameId);
	}
	
	@MessageMapping(GAME_MAPPING_PREFIX+"/{gameId}/join")
	@SendToUser(SERVER_PUBLISH_PREFIX+"/joined")
	public Player join(@DestinationVariable String gameId, PlayerInfo player) throws GameException {
		logger.info("player {} is joining the game {}", player, gameId);
		Player p = playerService.create(player);
		Game game = gameService.getGame(gameId);
		gameService.join(game, p);
		// update the context of the connected user
		userContext.setPlayerId(p.getId());
		userContext.setGameId(gameId);
		// auto-start
		if(gameOptions.isAutoStart() && !gameService.isStarted(game)) {
			gameService.start(game);
		}
		return p;
	}

	@MessageMapping(GAME_MAPPING_PREFIX+"/{gameId}/leave")
	@SendToUser(SERVER_PUBLISH_PREFIX+"/left")
	public Player quit(@DestinationVariable String gameId) throws GameException {
		Game game = gameService.getGame(gameId);
		Player player = gameService.getPlayer(game, userContext.getPlayerId());
		logger.info("player {} is leaving the game {}", player, game);
		gameService.quit(game, player);
		userContext.setGameId(null);
		// automatically stops the game when there is no more player
		if(gameOptions.isAutoStop() && gameService.isStarted(game) && game.getPlayers(p -> p instanceof Wizard).size()<=0) {
			gameService.stop(game);
		}
		return player;
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
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/player/joined", player);
	}

	@Override
	public void left(Player player, Game game) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/player/left", player);
	}
	
}
