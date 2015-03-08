package fr.sii.survival.core.listener.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;

public interface ActionListenerTrigger {
	
	public void triggerLifeUpdated(Game game, Player player, UpdateLife action);
	
	public void triggerPositionChanged(Game game, Player player, ChangePosition action);
	
	public void triggerImageMoved(Game game, MoveImage action);

	public void triggerStateChanged(Game game, Player player, ChangeStates action);
}
