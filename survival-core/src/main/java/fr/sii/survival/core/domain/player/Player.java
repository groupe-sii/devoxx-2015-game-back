package fr.sii.survival.core.domain.player;

public interface Player {
	/**
	 * The unique id of the player
	 * 
	 * @return the id of the player
	 */
	public String getId();

	/**
	 * Get the life of the player (current life and maximum life)
	 * 
	 * @return the life of the player
	 */
	public Life getLife();

	/**
	 * Get the current states applied to the player
	 * 
	 * @return the current states on the player
	 */
	public States getStates();

	/**
	 * Get the player information (name and avatar)
	 * 
	 * @return the player information
	 */
	public PlayerInfo getPlayerInfo();
}
