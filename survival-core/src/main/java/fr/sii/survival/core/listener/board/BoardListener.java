package fr.sii.survival.core.listener.board;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;

/**
 * Listener that trigger events when the board changes.
 * 
 * @author aurelien
 *
 */
public interface BoardListener {
	/**
	 * Fired when a player has moved.
	 * 
	 * @param player
	 *            the player who moved
	 * @param oldCell
	 *            the position of the player before the move
	 * @param newCell
	 *            the position of the player after the move
	 */
	public void moved(Player player, Cell oldCell, Cell newCell);

	/**
	 * Fired when a player has been added on the board.
	 * 
	 * @param player
	 *            the added player
	 * @param cell
	 *            the cell where the player has been added
	 */
	public void added(Player player, Cell cell);

	/**
	 * Fired when a player has been removed from the board.
	 * 
	 * @param player
	 *            the removed player
	 * @param cell
	 *            the cell where the player was before being removed
	 */
	public void removed(Player player, Cell cell);
}
