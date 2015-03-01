package fr.sii.survival.dto;

import java.util.List;

import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;

public class PlayerStateUpdate {
	/**
	 * The updated player
	 */
	private Player player;
	
	/**
	 * The list of modifications applied to the player states
	 */
	private List<StateChange> changes;

	public PlayerStateUpdate(Player player, List<StateChange> changes) {
		super();
		this.player = player;
		this.changes = changes;
	}

	public Player getPlayer() {
		return player;
	}

	public List<StateChange> getChanges() {
		return changes;
	}
}
