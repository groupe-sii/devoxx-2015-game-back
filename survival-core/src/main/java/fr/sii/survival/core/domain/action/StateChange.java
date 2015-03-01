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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((change == null) ? 0 : change.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StateChange other = (StateChange) obj;
		if (change != other.change)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StateChange [state=").append(state).append(", change=").append(change).append("]");
		return builder.toString();
	}
}
