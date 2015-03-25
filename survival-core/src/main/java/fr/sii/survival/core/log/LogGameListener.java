package fr.sii.survival.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.game.GameListener;

public class LogGameListener implements GameListener {
	private static final Logger LOG = LoggerFactory.getLogger(LogGameListener.class);

	@Override
	public void started(Game game) {
		LOG.info("game {} has started", game);
	}

	@Override
	public void stopped(Game game) {
		LOG.info("game {} has stopped", game);
	}

	@Override
	public void joined(Player player, Game game) {
		LOG.info("player {} has joined the game {}", player, game);
	}

	@Override
	public void left(Player player, Game game) {
		LOG.info("player {} has left the game {}", player, game);
	}

}
