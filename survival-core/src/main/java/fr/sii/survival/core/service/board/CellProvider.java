package fr.sii.survival.core.service.board;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;

/**
 * Provides a cell that is available on the board
 * 
 * @author Aurélien Baudet
 *
 */
public interface CellProvider {
	/**
	 * Get an available cell
	 * 
	 * @param board
	 *            the board that contains cell and game information
	 * @return the available cell
	 */
	public Cell getCell(Board board);
}
