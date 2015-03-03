package fr.sii.survival.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.sii.survival.WebSocketConfig;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.log.LogActionListener;
import fr.sii.survival.core.service.game.GameService;

@Controller
public class GameController{

	private static Logger logger = LoggerFactory.getLogger(LogActionListener.class);
	
	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	GameService gameService;
	
	@MessageMapping(WebSocketConfig.CLIENT_SEND_PREFIX + "/player/join")
	public void newPlayer(Player player) throws Exception{
		logger.debug("toto");
		gameService.newPlayer(player);
	}
	
	@MessageMapping(WebSocketConfig.CLIENT_SEND_PREFIX + "/player/quit")
	public void removePlayer(Player player) throws Exception{
		gameService.removePlayer(player);
	}

}
