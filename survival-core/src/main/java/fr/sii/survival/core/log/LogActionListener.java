package fr.sii.survival.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.AddImage;
import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.RemoveImage;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.action.ActionListener;

public class LogActionListener implements ActionListener {
	private static final Logger logger = LoggerFactory.getLogger(LogActionListener.class);

	@Override
	public void lifeUpdated(Game game, Player player, UpdateLife action) {
		logger.info("life update {} for player {}", action.getIncrement(), player);
	}

	@Override
	public void positionChanged(Game game, Player player, ChangePosition action) {
		logger.info("player {} moved from {} to {}", player, action.getStart(), action.getEnd());
	}

	@Override
	public void imageAdded(Game game, AddImage action) {
		logger.info("image {} add on {}", action.getImage(), action.getCell());
	}

	@Override
	public void imageMoved(Game game, MoveImage action) {
		logger.info("image {} moved from {} to {}", action.getImage(), action.getStart(), action.getEnd());
	}

	@Override
	public void imageRemoved(Game game, RemoveImage action) {
		logger.info("image {} removed from {}", action.getImage(), action.getCell());
	}

	@Override
	public void stateChanged(Game game, Player player, ChangeStates action) {
		logger.info("states {} applied to {}", action.getStateChanges(), player);
	}

}
