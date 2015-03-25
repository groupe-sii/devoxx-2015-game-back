package fr.sii.survival.core.exception;

import fr.sii.survival.core.domain.action.Action;

public class ActionExecutionException extends ActionException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1601301061827930042L;
	
	private final Action action;
	
	public ActionExecutionException(String message, Action action, Throwable cause) {
		super(message, cause);
		this.action = action;
	}

	public Action getAction() {
		return action;
	}
}
