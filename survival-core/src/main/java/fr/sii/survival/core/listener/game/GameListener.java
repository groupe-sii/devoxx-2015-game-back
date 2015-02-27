package fr.sii.survival.core.listener.game;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;

public interface GameListener {
	public void started(Game game);
	
	public void stopped(Game game);
	
	public void joined(Player player, Game game);
	
	public void quit(Player player, Game game);
}
