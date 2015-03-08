package fr.sii.survival.core.listener.board;

import fr.sii.survival.core.domain.Game;
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
	 * @param game
	 *            the game in which the player moved
	 * @param player
	 *            the player who moved
	 * @param oldCell
	 *            the position of the player before the move
	 * @param newCell
	 *            the position of the player after the move
	 */
	public void moved(Game game, Player player, Cell oldCell, Cell newCell);

	/**
	 * Fired when a player has been added on the board.
	 * 
	 * @param game
	 *            the game in which the player is added
	 * @param player
	 *            the added player
	 * @param cell
	 *            the cell where the player has been added
	 */
	public void added(Game game, Player player, Cell cell);

	/**
	 * Fired when a player has been removed from the board.
	 * 
	 * @param game
	 *            the game from which the player is removed
	 * @param player
	 *            the removed player
	 * @param cell
	 *            the cell where the player was before being removed
	 */
	public void removed(Game game, Player player, Cell cell);
}
