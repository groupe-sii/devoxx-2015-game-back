package fr.sii.survival.core.domain.player;

import java.util.ArrayList;
import java.util.List;

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
	private List<String> states;

	public PlayerStates(List<String> states) {
		super();
		this.states = states;
	}

	public PlayerStates() {
		this(new ArrayList<>());
	}

	/**
	 * Get the list of states
	 * 
	 * @return the list of states
	 */
	public List<String> getStates() {
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
		if (!states.contains(state)) {
			states.add(state);
			return true;
		}
		return false;
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
