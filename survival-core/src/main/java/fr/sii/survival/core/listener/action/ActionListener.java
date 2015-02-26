package fr.sii.survival.core.listener.action;

import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;

public interface ActionListener {
	public void lifeUpdated(Player player, UpdateLife action);
	
	public void positionChanged(Player player, ChangePosition action);
	
	public void imageMoved(MoveImage action);
}
