package fr.sii.survival.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.board.BoardListener;
import fr.sii.survival.dto.PlayerMoved;

@Controller
@MessageMapping("board")
public class GameBoardController implements BoardListener {
	@SendTo("board/moved")
	public PlayerMoved notifyMoved(Player player, Cell oldCell, Cell newCell) {
		return new PlayerMoved(player, oldCell, newCell);
	}

	@SendTo("board/added")
	public PlayerMoved notifyAdded(Player player, Cell cell) {
		return new PlayerMoved(player, null, cell);
	}

	@SendTo("board/removed")
	public PlayerMoved notifyRemoved(Player player, Cell cell) {
		return new PlayerMoved(player, cell, null);
	}
	
	
	@Override
	public void moved(Player player, Cell oldCell, Cell newCell) {
		notifyMoved(player, oldCell, newCell);
	}

	@Override
	public void added(Player player, Cell cell) {
		notifyAdded(player, cell);
	}

	@Override
	public void removed(Player player, Cell cell) {
		notifyRemoved(player, cell);
	}
}
