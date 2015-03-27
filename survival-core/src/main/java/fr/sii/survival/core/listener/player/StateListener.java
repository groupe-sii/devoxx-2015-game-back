package fr.sii.survival.core.listener.player;

import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;

/**
 * Listener that only triggers when the provided state has been added or
 * removed.
 * 
 * @author Aurélien Baudet
 *
 */
public abstract class StateListener extends AbstractSingleGamePlayerListener {
	private String state;

	public StateListener(Game game, String state) {
		super(game);
		this.state = state;
	}

	@Override
	protected void statesChanged(Player player, List<StateChange> changes) {
		for (StateChange change : changes) {
			if (state.equals(change.getState())) {
				stateChanged(player, change);
			}
		}
	}

	protected abstract void stateChanged(Player player, StateChange change);

	@Override
	protected void dead(Player player) {
		// nothing to do
	}

	@Override
	protected void revived(Player player) {
		// nothing to do
	}

	@Override
	protected void hit(Player player, int damage) {
		// nothing to do
	}

	@Override
	protected void healed(Player player, int amount) {
		// nothing to do
	}

	@Override
	protected void maxLifeChanged(Player player, int amount) {
		// nothing to do
	}

}
