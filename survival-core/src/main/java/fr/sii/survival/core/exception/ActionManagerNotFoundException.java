package fr.sii.survival.core.exception;

import fr.sii.survival.core.domain.action.Action;

public class ActionManagerNotFoundException extends ActionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1341467474676161054L;

	private Action action;
	
	public ActionManagerNotFoundException(String message, Action action) {
		super(message);
		this.action = action;
	}

	public Action getAction() {
		return action;
	}
}
