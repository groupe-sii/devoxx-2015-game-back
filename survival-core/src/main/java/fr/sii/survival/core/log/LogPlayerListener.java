package fr.sii.survival.core.log;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.player.PlayerListener;

public class LogPlayerListener implements PlayerListener {
	private static Logger logger = LoggerFactory.getLogger(LogPlayerListener.class);

	@Override
	public void dead(Player player) {
		logger.info("player {} is dead", player);
	}

	@Override
	public void revived(Player player) {
		logger.info("player {} comes back to life", player);
	}

	@Override
	public void hit(Player player, int damage) {
		logger.info("player {} is damaged by {} points", player, damage);
	}

	@Override
	public void healed(Player player, int amount) {
		logger.info("player {} is healed by {} points", player, amount);
	}

	@Override
	public void statesChanged(Player player, List<StateChange> changes) {
		logger.info("player {} has new states {}", player, changes);
	}

}
