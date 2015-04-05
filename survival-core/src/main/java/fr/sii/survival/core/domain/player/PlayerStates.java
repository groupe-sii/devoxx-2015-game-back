package fr.sii.survival.core.domain.player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Groups the list of states for a player
 * 
 * @author Aurélien Baudet
 *
 */
public class PlayerStates {
	/**
	 * The list of states
	 */
	private Set<String> states;

	public PlayerStates(String... states) {
		this(new HashSet<>(Arrays.asList(states)));
	}

	public PlayerStates(Set<String> states) {
		super();
		this.states = states;
	}

	public PlayerStates() {
		this(new HashSet<>());
	}

	/**
	 * Get the list of states
	 * 
	 * @return the list of states
	 */
	public Set<String> getStates() {
		return states;
	}

	/**
	 * Adds a state to the list of states only if not already present. Returns
	 * true if the state has been added, false otherwise (when already present).
	 * 
	 * @param state
	 *            the state to add
	 * @return true if the state has been added, false otherwise
	 */
	public boolean addState(String state) {
		return states.add(state);
	}

	/**
	 * Removes a state from the list of states if exists.
	 * 
	 * @param state
	 *            the state to remove
	 * @return true if state was removed, false otherwise
	 */
	public boolean removeState(String state) {
		return states.remove(state);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(states);
		return builder.toString();
	}
}
