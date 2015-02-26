package fr.sii.survival.core.domain.action;

import fr.sii.survival.core.domain.Cell;


public class UpdateMaxLife extends UpdateLife {
	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	@Deprecated
	public UpdateMaxLife() {
		super();
	}

	public UpdateMaxLife(int increment, Cell cell) {
		super(increment, cell);
	}
}
