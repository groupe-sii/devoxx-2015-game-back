package fr.sii.survival.core.domain.action;

public class StateChange {
	public enum Change {
		ADD, REMOVE
	}

	/**
	 * The state to add or remove
	 */
	private String state;

	/**
	 * The action to apply on the state (add or remove)
	 */
	private Change change;

	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	@Deprecated
	public StateChange() {
		super();
	}
	
	/**
	 * Add a new state
	 * 
	 * @param state
	 *            the state to add
	 */
	public StateChange(String state) {
		this(state, Change.ADD);
	}

	public StateChange(String state, Change change) {
		super();
		this.state = state;
		this.change = change;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Change getChange() {
		return change;
	}

	public void setChange(Change change) {
		this.change = change;
	}
}
