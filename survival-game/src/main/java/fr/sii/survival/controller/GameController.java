package fr.sii.survival.controller;

import static fr.sii.survival.config.GameConfiguration.GAME_PUBLISH_PREFIX;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import fr.sii.survival.config.options.LifeOptions;
import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.PlayerInfo;
import fr.sii.survival.core.domain.player.SimpleWizard;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.game.GameListener;
import fr.sii.survival.core.service.game.GameService;
import fr.sii.survival.dto.PlayerAndGame;
import fr.sii.survival.session.UserContext;

@Controller
public class GameController extends ErrorController implements GameListener {

	private static final Logger logger = LoggerFactory.getLogger(GameController.class);

	@Autowired
	SimpMessagingTemplate template;

	@Autowired
	GameService gameService;

	@Autowired
	LifeOptions lifeOptions;

	@Autowired
	UserContext userContext;

	
	@MessageMapping("/info")
	@SendToUser("/info")
	public Game info() {
		return gameService.getGame();
	}
	
	@MessageMapping("/player/join")
	@SendToUser("/player/join")
	public Player join(PlayerInfo player) throws GameException {
		logger.info("player {} is joining the game", player);
		SimpleWizard p = new SimpleWizard(player, lifeOptions.getDefaultLife());
		gameService.join(p);
		userContext.setPlayerId(p.getId());
		// auto-start
		if(!gameService.isStarted()) {
			gameService.start();
		}
		return p;
	}

	@MessageMapping("/player/quit")
	@SendToUser("/player/quit")
	public Player quit() throws GameException {
		String playerId = userContext.getPlayerId();
		if(playerId!=null) {
			Player player = gameService.getPlayer(playerId);
			if(player!=null) {
				logger.info("player {} is quitting the game", player);
				gameService.quit(player);
				// TODO: auto-stop when nobody in game
				return player;
			}
		}
		return null;
	}
	
	@Override
	public void started(Game game) {
		template.convertAndSend(GAME_PUBLISH_PREFIX+"/started", game);
	}

	@Override
	public void stopped(Game game) {
		template.convertAndSend(GAME_PUBLISH_PREFIX+"/stopped", game);
	}

	@Override
	public void joined(Player player, Game game) {
		template.convertAndSend(GAME_PUBLISH_PREFIX+"/joined", new PlayerAndGame(player, game));
	}

	@Override
	public void leaved(Player player, Game game) {
		template.convertAndSend(GAME_PUBLISH_PREFIX+"/leaved", new PlayerAndGame(player, game));
	}
}
