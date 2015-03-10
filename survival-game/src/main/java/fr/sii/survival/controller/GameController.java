package fr.sii.survival.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.PlayerInfo;
import fr.sii.survival.core.domain.player.SimpleWizard;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.service.game.GameService;
import fr.sii.survival.session.UserContext;

@Controller
public class GameController extends ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(GameController.class);

	@Autowired
	SimpMessagingTemplate template;

	@Autowired
	GameService gameService;

	@Value("${game.life.default}")
	int defaultLife;

	@Autowired
	UserContext userContext;

	@MessageMapping("/player/join")
	public void join(PlayerInfo player) throws GameException {
		logger.info("player {} is joining the game", player);
		SimpleWizard p = new SimpleWizard(player, defaultLife);
		gameService.join(p);
		userContext.setPlayerId(p.getId());
	}

	@MessageMapping("/player/quit")
	public void quit() throws GameException {
		String playerId = userContext.getPlayerId();
		if(playerId!=null) {
			Player player = gameService.getPlayer(playerId);
			if(player!=null) {
				logger.info("player {} is quitting the game", player);
				gameService.quit(player);
			}
		}
	}
}
