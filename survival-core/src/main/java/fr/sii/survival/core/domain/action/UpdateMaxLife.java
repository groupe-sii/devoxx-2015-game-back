package fr.sii.survival.core.domain.action;

import fr.sii.survival.core.domain.board.Cell;


public class UpdateMaxLife extends UpdateLife {

	public UpdateMaxLife(int increment, Cell cell) {
		super(increment, cell);
	}
}
