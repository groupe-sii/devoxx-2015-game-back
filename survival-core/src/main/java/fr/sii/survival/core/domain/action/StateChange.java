package fr.sii.survival.core.domain.action;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Represents a single change (add or remove) to apply on the provided state
 * 
 * @author aurelien
 *
 */
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
		return new HashCodeBuilder().append(state).append(change).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof StateChange)) {
			return false;
		}
		StateChange other = (StateChange) obj;
		return new EqualsBuilder().append(state, other.state).append(change, other.change).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StateChange [state=").append(state).append(", change=").append(change).append("]");
		return builder.toString();
	}
}
