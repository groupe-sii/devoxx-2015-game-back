package fr.sii.survival.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.board.BoardListener;

public class LogBoardListener implements BoardListener {
	private static final Logger logger = LoggerFactory.getLogger(LogBoardListener.class);

	@Override
	public void moved(Game game, Player player, Cell oldCell, Cell newCell) {
		logger.info("player {} moved from {} to {}", player, oldCell, newCell);
	}

	@Override
	public void added(Game game, Player player, Cell cell) {
		logger.info("player {} added on {}", player, cell);
	}

	@Override
	public void removed(Game game, Player player, Cell cell) {
		logger.info("player {} removed from {}", player, cell);
	}

}
