package fr.sii.survival.core.listener.player;

public interface PlayerListenerRegistry {
	
	public void addPlayerListener(PlayerListener listener);
	
	public void removePlayerListener(PlayerListener listener);

}
