package fr.sii.survival.core.listener.action;

import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;

public class ActionListenerAdapter implements ActionListener {

	@Override
	public void lifeUpdated(Player player, UpdateLife action) {

	}

	@Override
	public void positionChanged(Player player, ChangePosition action) {

	}

	@Override
	public void imageMoved(MoveImage action) {

	}

	@Override
	public void stateChanged(Player player, ChangeStates action) {
		
	}
}
