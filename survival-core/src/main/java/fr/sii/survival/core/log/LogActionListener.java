package fr.sii.survival.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.AddImage;
import fr.sii.survival.core.domain.action.UpdatePosition;
import fr.sii.survival.core.domain.action.UpdateStates;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.RemoveImage;
import fr.sii.survival.core.domain.action.StartAnimation;
import fr.sii.survival.core.domain.action.StopAnimation;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.action.ActionListener;

public class LogActionListener implements ActionListener {
	private static final Logger LOG = LoggerFactory.getLogger(LogActionListener.class);

	@Override
	public void lifeUpdated(Game game, Player player, UpdateLife action) {
		LOG.info("life update {} for player {}", action.getIncrement(), player);
	}

	@Override
	public void positionChanged(Game game, Player player, UpdatePosition action) {
		LOG.info("player {} moved from {} to {}", player, action.getStart(), action.getEnd());
	}

	@Override
	public void imageAdded(Game game, AddImage action) {
		LOG.info("image {} add on {}", action.getImage(), action.getCell());
	}

	@Override
	public void imageMoved(Game game, MoveImage action) {
		LOG.info("image {} moved from {} to {}", action.getImage(), action.getStart(), action.getEnd());
	}

	@Override
	public void imageRemoved(Game game, RemoveImage action) {
		LOG.info("image {} removed from {}", action.getImage(), action.getCell());
	}

	@Override
	public void stateChanged(Game game, Player player, UpdateStates action) {
		LOG.info("states {} applied to {}", action.getStateChanges(), player);
	}

	@Override
	public void animationStarted(Game game, StartAnimation action) {
		LOG.info("animation {} started on game {}", action.getName(), game);
	}

	@Override
	public void animationStopped(Game game, StopAnimation action) {
		LOG.info("animation {} stopped on game {}", action.getName(), game);
	}

}
