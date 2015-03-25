package fr.sii.survival.core.domain.action;

import fr.sii.survival.core.domain.board.Cell;


public class UpdateCurrentLife extends UpdateLife {

	public UpdateCurrentLife(int increment, Cell cell) {
		super(increment, cell);
	}
}
