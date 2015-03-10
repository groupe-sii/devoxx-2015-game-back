package fr.sii.survival.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.game.GameListener;

public class LogGameListener implements GameListener {
	private static Logger logger = LoggerFactory.getLogger(LogGameListener.class);

	@Override
	public void started(Game game) {
		logger.info("game {} has started", game);
	}

	@Override
	public void stopped(Game game) {
		logger.info("game {} has stopped", game);
	}

	@Override
	public void joined(Player player, Game game) {
		logger.info("player {} has joined the game {}", player, game);
	}

	@Override
	public void leaved(Player player, Game game) {
		logger.info("player {} has leaved the game {}", player, game);
	}

}
