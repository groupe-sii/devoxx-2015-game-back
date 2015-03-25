package fr.sii.survival.controller;

import static fr.sii.survival.config.WebSocketConfig.SERVER_PUBLISH_PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.board.BoardListener;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.dto.PlayerMoved;

@Controller
public class BoardController extends ErrorController implements BoardListener {
	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	BoardService boardService;
	
	@Override
	public void moved(Game game, Player player, Cell oldCell, Cell newCell) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/player/moved", new PlayerMoved(player, oldCell, newCell));
	}

	@Override
	public void added(Game game, Player player, Cell cell) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/player/added", new PlayerMoved(player, null, cell));
	}

	@Override
	public void removed(Game game, Player player, Cell cell) {
		template.convertAndSend(SERVER_PUBLISH_PREFIX+"/"+game.getId()+"/player/removed", new PlayerMoved(player, cell, null));
	}
}
