package fr.sii.survival.core.listener.game;

public interface GameListenerRegistry {
	public void addGameListener(GameListener listener);
	
	public void removeGameListener(GameListener listener);
}
