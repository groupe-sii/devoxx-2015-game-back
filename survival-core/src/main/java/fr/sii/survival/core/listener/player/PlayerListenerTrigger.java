package fr.sii.survival.core.listener.player;

import fr.sii.survival.core.domain.player.Player;

public interface PlayerListenerTrigger {
	public void triggerDead(Player player);
	
	public void triggerRevived(Player player);
	
	public void triggerHit(Player player, int damage);
	
	public void triggerHealed(Player player, int amount);
}
