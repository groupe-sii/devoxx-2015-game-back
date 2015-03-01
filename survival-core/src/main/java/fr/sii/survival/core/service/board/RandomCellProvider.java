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
		int x = (int) Math.floor(Math.random()*(board.getWidth()-1));
		int y = (int) Math.floor(Math.random()*(board.getHeight()-1));
		return new Cell(x, y);
	}

}
