package fr.sii.survival.core.listener.action;

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

public class ActionListenerAdapter implements ActionListener {

	@Override
	public void lifeUpdated(Game game, Player player, UpdateLife action) {
		// nothing to do
	}

	@Override
	public void positionChanged(Game game, Player player, UpdatePosition action) {
		// nothing to do
	}

	@Override
	public void imageAdded(Game game, AddImage action) {
		// nothing to do
	}

	@Override
	public void imageMoved(Game game, MoveImage action) {
		// nothing to do
	}

	@Override
	public void imageRemoved(Game game, RemoveImage action) {
		// nothing to do
	}

	@Override
	public void stateChanged(Game game, Player player, UpdateStates action) {
		// nothing to do
	}

	@Override
	public void animationStarted(Game game, StartAnimation action) {
		// nothing to do
	}

	@Override
	public void animationStopped(Game game, StopAnimation action) {
		// nothing to do
	}
}
