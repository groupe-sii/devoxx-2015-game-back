package fr.sii.survival.core.listener.player;

import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;

public interface PlayerListenerTrigger {
	public void triggerDead(Game game, Player player);
	
	public void triggerRevived(Game game, Player player);
	
	public void triggerHit(Game game, Player player, int damage);
	
	public void triggerHealed(Game game, Player player, int amount);
	
	public void triggerStates(Game game, Player player, List<StateChange> changes);

	public void triggerMaxLifeChanged(Game game, Player player, int amount);
}
