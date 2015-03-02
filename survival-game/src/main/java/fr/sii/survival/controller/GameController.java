package fr.sii.survival.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.GameMessage;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.service.game.GameService;

@Controller
public class GameController{

	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	GameService gameService;
	
	@MessageMapping("/game")
    @SendTo("/topic/game/message")
	public GameMessage gameMessage(GameMessage message) throws Exception{
		Thread.sleep(3000); // simulated delay
        return new GameMessage(message.getAction(), message.getData());
	}
	
	@MessageMapping("/player/new")
	public void newPlayer(Player player) throws Exception{
		gameService.newPlayer(player);
	}
	
	@MessageMapping("/player/quit")
	public void removePlayer(Player player) throws Exception{
		gameService.removePlayer(player);
	}

}
