package fr.sii.survival.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import fr.sii.survival.domain.GameMessage;

@Controller
public class GameController {

	@MessageMapping("/game")
    @SendTo("/topic/game/message")
	public GameMessage gameMessage(GameMessage message) throws Exception{
		Thread.sleep(3000); // simulated delay
        return new GameMessage(message.getAction(), message.getData());
	}
}
