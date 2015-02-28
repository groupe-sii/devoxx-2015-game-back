package fr.sii.survival.core.service.board;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;

/**
 * Cell provider that always provides the same position.
 * 
 * @author aurelien
 *
 */
public class FixedCellProvider implements CellProvider {

	private int x;

	private int y;

	public FixedCellProvider() {
		this(0, 0);
	}

	public FixedCellProvider(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public Cell getCell(Board board) {
		return new Cell(x, y);
	}

}
