package fr.sii.survival.core.domain.action;

import fr.sii.survival.core.domain.Cell;


public class UpdateCurrentLife extends UpdateLife {
	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	@Deprecated
	public UpdateCurrentLife() {
		super();
	}

	public UpdateCurrentLife(int increment, Cell cell) {
		super(increment, cell);
	}
}
