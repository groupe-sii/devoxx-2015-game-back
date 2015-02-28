package fr.sii.survival.core.service.board;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;

/**
 * Cell provider that randomly provides cell position.
 * 
 * @author aurelien
 *
 */
public class RandomCellProvider implements CellProvider {

	@Override
	public Cell getCell(Board board) {
		int x = (int) Math.floor(Math.random()*board.getWidth());
		int y = (int) Math.floor(Math.random()*board.getHeight());
		return new Cell(x, y);
	}

}
