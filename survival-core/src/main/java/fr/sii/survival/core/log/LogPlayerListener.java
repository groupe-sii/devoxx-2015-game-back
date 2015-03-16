package fr.sii.survival.core.log;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.player.PlayerListener;

public class LogPlayerListener implements PlayerListener {
	private static Logger logger = LoggerFactory.getLogger(LogPlayerListener.class);

	@Override
	public void dead(Game game, Player player) {
		logger.info("player {} is dead", player);
	}

	@Override
	public void revived(Game game, Player player) {
		logger.info("player {} comes back to life", player);
	}

	@Override
	public void hit(Game game, Player player, int damage) {
		logger.info("player {} is damaged by {} points", player, damage);
	}

	@Override
	public void healed(Game game, Player player, int amount) {
		logger.info("player {} is healed by {} points", player, amount);
	}

	@Override
	public void statesChanged(Game game, Player player, List<StateChange> changes) {
		logger.info("player {} has new states {}", player, changes);
	}

	@Override
	public void maxLifeChanged(Game game, Player player, int amount) {
		logger.info("player {} maximum life updated by {} points", player, amount);
	}

}
