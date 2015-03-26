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

public interface ActionListenerTrigger {
	
	public void triggerLifeUpdated(Game game, Player player, UpdateLife action);
	
	public void triggerPositionChanged(Game game, Player player, UpdatePosition action);
	
	public void triggerImageAdded(Game game, AddImage action);

	public void triggerImageMoved(Game game, MoveImage action);

	public void triggerImageRemoved(Game game, RemoveImage action);

	public void triggerStateChanged(Game game, Player player, UpdateStates action);

	public void triggerAnimationStarted(Game game, StartAnimation action);

	public void triggerAnimationStopped(Game game, StopAnimation action);
}
