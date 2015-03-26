package fr.sii.survival.core.domain.action;

import fr.sii.survival.core.domain.board.Cell;

public class UpdatePosition extends MoveActionBase {

	public UpdatePosition(Cell start, Cell end) {
		super(start, end);
	}

}
