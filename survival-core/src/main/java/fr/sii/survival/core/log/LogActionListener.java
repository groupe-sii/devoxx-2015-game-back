package fr.sii.survival.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.action.ActionListener;

public class LogActionListener implements ActionListener {
	private static Logger logger = LoggerFactory.getLogger(LogActionListener.class);

	@Override
	public void lifeUpdated(Player player, UpdateLife action) {
		logger.info("life update {} for player {}", action.getIncrement(), player);
	}

	@Override
	public void positionChanged(Player player, ChangePosition action) {
		logger.info("player {} moved from {} to {}", player, action.getStart(), action.getEnd());
	}

	@Override
	public void imageMoved(MoveImage action) {
		logger.info("image moved from {} to {}", action.getStart(), action.getEnd());
	}

	@Override
	public void stateChanged(Player player, ChangeStates action) {
		logger.info("states {} applied to {}", action.getStateChanges(), player);
	}

}
