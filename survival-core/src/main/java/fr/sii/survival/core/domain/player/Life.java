package fr.sii.survival.core.domain.player;

public interface Life {
	/**
	 * Get current number of points of life
	 * 
	 * @return the current points of life
	 */
	public int getCurrent();

	/**
	 * Set the new current number of points of life
	 * 
	 * @param life
	 *            the new life
	 */
	public void setCurrent(int life);

	/**
	 * Update the current life with the provided delta
	 * 
	 * @param delta
	 *            the delta to apply on the life
	 * @return the new life value
	 */
	public int updateCurrent(int delta);

	/**
	 * Get maximum number of points of life
	 * 
	 * @return the maximum points of life
	 */
	public int getMax();

	/**
	 * Set the new maximum number of points of life
	 * 
	 * @param max
	 *            the new max
	 */
	public void setMax(int max);

	/**
	 * Update the maximum life with the provided delta
	 * 
	 * @param delta
	 *            the delta to apply on the maximum life
	 * @return the new maximum value
	 */
	public int updateMax(int delta);
}
