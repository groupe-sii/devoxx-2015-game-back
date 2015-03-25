package fr.sii.survival.core.service.board.rule;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.service.board.Direction;
import fr.sii.survival.core.service.rule.Rule;

public interface AllowMoveRule extends Rule {
	/**
	 * Check if the player is allowed to move
	 * 
	 * @param board
	 *            the current board
	 * @param player
	 *            the player that wants to move to
	 * @param cell
	 *            the cell where the player wants to move to
	 * @return true if the player is allowed to move to, false otherwise
	 */
	public boolean isMoveAllowed(Board board, Player player, Cell cell);

	/**
	 * Check if the player is allowed to move
	 * 
	 * @param board
	 *            the current board
	 * @param player
	 *            the player that wants to move
	 * @param direction
	 *            the direction where the player wants to move to
	 * @return true if the player is allowed to move to, false otherwise
	 */
	public boolean isMoveAllowed(Board board, Player player, Direction direction);

}
