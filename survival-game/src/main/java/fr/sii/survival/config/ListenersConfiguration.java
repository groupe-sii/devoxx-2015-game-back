package fr.sii.survival.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.controller.ActionController;
import fr.sii.survival.controller.BoardController;
import fr.sii.survival.controller.MessageController;
import fr.sii.survival.controller.PlayerController;
import fr.sii.survival.core.log.LogActionListener;
import fr.sii.survival.core.log.LogBoardListener;
import fr.sii.survival.core.log.LogMessageListener;
import fr.sii.survival.core.log.LogPlayerListener;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.message.MessageService;
import fr.sii.survival.core.service.player.PlayerService;

@Configuration
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
	
	
	@PostConstruct
	public void init() {
		playerService.addPlayerListener(new LogPlayerListener());
		playerService.addPlayerListener(playerController);
		messageService.addMessageListener(new LogMessageListener());
		messageService.addMessageListener(messageController);
		boardService.addBoardListener(new LogBoardListener());
		boardService.addBoardListener(boardController);
		actionService.addActionListener(new LogActionListener());
		actionService.addActionListener(actionController);
	}

}
