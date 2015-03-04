package fr.sii.survival.controller;

import static fr.sii.survival.config.BoardConfiguration.BOARD_MAPPING_PREFIX;
import static fr.sii.survival.config.BoardConfiguration.BOARD_PUBLISH_PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.board.BoardListener;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.dto.PlayerMoved;

@Controller
public class BoardController implements BoardListener {
	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	BoardService boardService;
	
	@MessageMapping(BOARD_MAPPING_PREFIX+"/up")
	public void moveUp() {
		// TODO: get current player
		// TODO: get current cell of the player
		// TODO: return new position of the player ?
//		boardService.move(player, cell);
	}

	@MessageMapping(BOARD_MAPPING_PREFIX+"/down")
	public void moveDown() {
//		actionService.execute(action);
	}

	@MessageMapping(BOARD_MAPPING_PREFIX+"/left")
	public void moveLeft() {
//		actionService.execute(action);
	}

	@MessageMapping(BOARD_MAPPING_PREFIX+"/right")
	public void moveRight() {
//		actionService.execute(action);
	}

	@Override
	public void moved(Player player, Cell oldCell, Cell newCell) {
		template.convertAndSend(BOARD_PUBLISH_PREFIX+"/moved", new PlayerMoved(player, oldCell, newCell));
	}

	@Override
	public void added(Player player, Cell cell) {
		template.convertAndSend(BOARD_PUBLISH_PREFIX+"/added", new PlayerMoved(player, null, cell));
	}

	@Override
	public void removed(Player player, Cell cell) {
		template.convertAndSend(BOARD_PUBLISH_PREFIX+"/removed", new PlayerMoved(player, cell, null));
	}
}
