package fr.sii.survival.core.service.board;

import java.util.List;

import fr.sii.survival.core.domain.Cell;
import fr.sii.survival.core.domain.player.Player;

public interface BoardService {
	/**
	 * Provide the list of players that are on the provided cell. If no player
	 * is on the cell, then the list is empty.
	 * 
	 * @param cell
	 *            the cell position
	 * @return the list of players on the cell
	 */
	public List<Player> getPlayers(Cell cell);

	/**
	 * Move a player to another cell. Checks if the player can move. If the
	 * player can move, then the returned cell points to the new location. If
	 * the player can't move, then the returned cell points to the previous
	 * location.
	 * 
	 * @param player
	 *            the player to move
	 * @param cell
	 *            the new cell position
	 * @return the new cell position if the player can move to it, the old cell
	 *         if the player can't move
	 */
	public Cell move(Player player, Cell cell);
}
