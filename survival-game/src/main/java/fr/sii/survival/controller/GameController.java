package fr.sii.survival.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.SimpleWizard;
import fr.sii.survival.core.service.game.GameService;

@Controller
public class GameController{

	private static Logger logger = LoggerFactory.getLogger(GameController.class);
	
	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	GameService gameService;
	
	@MessageMapping("/player/join")
	public void newPlayer(Player player) throws Exception{
		gameService.newPlayer(player);
	}
	
	@MessageMapping("/player/quit")
	public void removePlayer(Player player) throws Exception{
		gameService.removePlayer(player);
	}
	
	@MessageMapping("/player/test")
	@SendTo("/topic/game/action")
	public Player test() throws Exception{
		return new SimpleWizard();
	}

}
