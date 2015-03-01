package fr.sii.survival.core.domain.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.sii.survival.core.domain.player.Player;

public class ChangeStates implements Action {
	/**
	 * The player to update
	 */
	private Player player;

	/**
	 * A list of changes to apply. Each change provide the name of the state and
	 * the action to apply on the provided state (add or remove)
	 */
	private List<StateChange> stateChanges;

	public ChangeStates(Player player, StateChange... changes) {
		this(player, new ArrayList<>(Arrays.asList(changes)));
	}

	public ChangeStates(Player player, List<StateChange> stateChanges) {
		super();
		this.player = player;
		this.stateChanges = stateChanges;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<StateChange> getStateChanges() {
		return stateChanges;
	}

	public void setStateChanges(List<StateChange> stateChanges) {
		this.stateChanges = stateChanges;
	}

	public void addStateChange(StateChange stateChange) {
		stateChanges.add(stateChange);
	}
}
