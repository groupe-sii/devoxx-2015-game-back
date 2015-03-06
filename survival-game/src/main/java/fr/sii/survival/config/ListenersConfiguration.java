package fr.sii.survival.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import fr.sii.survival.controller.ActionController;
import fr.sii.survival.controller.BoardController;
import fr.sii.survival.controller.GameController;
import fr.sii.survival.controller.MessageController;
import fr.sii.survival.controller.PlayerController;
import fr.sii.survival.core.log.LogActionListener;
import fr.sii.survival.core.log.LogBoardListener;
import fr.sii.survival.core.log.LogGameListener;
import fr.sii.survival.core.log.LogMessageListener;
import fr.sii.survival.core.log.LogPlayerListener;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.game.GameService;
import fr.sii.survival.core.service.message.MessageService;
import fr.sii.survival.core.service.player.PlayerService;

@Configuration
@Component
public class ListenersConfiguration {

	@Autowired
	PlayerService playerService;
	
	@Autowired
	PlayerController playerController;

	@Autowired
	MessageService messageService;
	
	@Autowired
	MessageController messageController;
	
	@Autowired
	BoardController boardController;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	ActionService actionService;
	
	@Autowired
	ActionController actionController;
	
	@Autowired
	GameService gameService;
	
	@Autowired
	GameController gameController;
	
	@Value("${logs.listeners.enable}")
	boolean enableLogs;
	
	@PostConstruct
	public void init() {
		if(enableLogs) {
			playerService.addPlayerListener(new LogPlayerListener());
			messageService.addMessageListener(new LogMessageListener());
			boardService.addBoardListener(new LogBoardListener());
			actionService.addActionListener(new LogActionListener());
			gameService.addGameListener(new LogGameListener());
		}
		playerService.addPlayerListener(playerController);
		messageService.addMessageListener(messageController);
		boardService.addBoardListener(boardController);
		actionService.addActionListener(actionController);
		gameService.addGameListener(gameController);
	}

}
