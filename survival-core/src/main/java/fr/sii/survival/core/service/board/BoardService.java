package fr.sii.survival.core.service.board;

import java.util.List;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.board.BoardListenerRegistry;

public interface BoardService extends BoardListenerRegistry {
	/**
	 * Create a new board
	 * 
	 * @return the created board
	 */
	public Board create();

	/**
	 * Provide the list of players that are on the provided cell. If no player
	 * is on the cell, then the list is empty.
	 * 
	 * @param board
	 *            the board where to look for players
	 * @param cell
	 *            the cell position
	 * @return the list of players on the cell
	 */
	public List<Player> getPlayers(Board board, Cell cell);

	/**
	 * Move a player to another cell. Checks if the player can move. If the
	 * player can move, then the returned cell points to the new location. If
	 * the player can't move, then the returned cell points to the previous
	 * location.
	 * 
	 * @param board
	 *            the board where to look for players
	 * @param player
	 *            the player to move
	 * @param cell
	 *            the new cell position
	 * @return the new cell position if the player can move to it, the old cell
	 *         if the player can't move
	 * @throws GameException
	 *             when error occurred while moving the player
	 */
	public Cell move(Board board, Player player, Cell cell) throws GameException;

	/**
	 * Move the player one cell up/down/left/right. If the player can't move
	 * up/down/left/right, then the player position is not changed and this
	 * method returns the previous cell
	 * 
	 * @param board
	 *            the board where to look for players
	 * @param player
	 *            the player to move
	 * @param direction
	 *            the direction to move the player to
	 * @return true if the player has moved, false otherwise
	 * @throws GameException
	 *             when error occurred while moving the player
	 */
	public Cell move(Board board, Player player, Direction direction) throws GameException;

	/**
	 * Adds a player on the board. Automatically selects a cell where the player
	 * will be placed.
	 * 
	 * @param board
	 *            the board where to add the player
	 * @param player
	 *            the player to add
	 * @return the cell where the player is added
	 * @throws GameException
	 *             when error occurred while adding the player
	 */
	public Cell add(Board board, Player player) throws GameException;

	/**
	 * Removes a player from the board.
	 * 
	 * @param board
	 *            the board where to remove the player from
	 * @param player
	 *            the player to remove
	 * @return the cell where the player was before remove. If the player was
	 *         not on the board, then returns null
	 * @throws GameException
	 *             when error occurred while removing the player
	 */
	public Cell remove(Board board, Player player) throws GameException;

	/**
	 * Search the position of the player in the game.
	 * 
	 * @param board
	 *            the board where to find player position
	 * @param player
	 *            the player to look for
	 * @return the cell if the player is found, null otherwise
	 */
	public Cell getCell(Board board, Player player);
}
