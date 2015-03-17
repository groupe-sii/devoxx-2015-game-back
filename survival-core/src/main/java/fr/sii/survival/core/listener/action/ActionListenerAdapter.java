package fr.sii.survival.core.listener.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.AddImage;
import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.RemoveImage;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;

public class ActionListenerAdapter implements ActionListener {

	@Override
	public void lifeUpdated(Game game, Player player, UpdateLife action) {

	}

	@Override
	public void positionChanged(Game game, Player player, ChangePosition action) {

	}

	@Override
	public void imageAdded(Game game, AddImage action) {
		
	}

	@Override
	public void imageMoved(Game game, MoveImage action) {

	}

	@Override
	public void imageRemoved(Game game, RemoveImage action) {
		
	}

	@Override
	public void stateChanged(Game game, Player player, ChangeStates action) {
		
	}
}
