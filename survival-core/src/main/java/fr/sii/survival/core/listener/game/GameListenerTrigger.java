package fr.sii.survival.core.listener.game;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;

public interface GameListenerTrigger {
	public void triggerStarted(Game game);
	
	public void triggerStopped(Game game);
	
	public void triggerJoined(Player player, Game game);
	
	public void triggerLeaved(Player player, Game game);
}
