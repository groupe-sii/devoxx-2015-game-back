package fr.sii.survival.core.domain.player;

public interface Player {
	/**
	 * Get the life of the player (current life and maximum life)
	 * 
	 * @return the life of the player
	 */
	public Life getLife();

	/**
	 * Set the new life for the player
	 * 
	 * @param life
	 *            the new life of the player
	 */
	public void setLife(Life life);
}
