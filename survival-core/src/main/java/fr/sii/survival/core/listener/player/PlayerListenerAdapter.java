package fr.sii.survival.core.listener.player;

import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;

public class PlayerListenerAdapter implements PlayerListener {

	@Override
	public void dead(Game game, Player player) {
		// nothing to do
	}

	@Override
	public void revived(Game game, Player player) {
		// nothing to do
	}

	@Override
	public void hit(Game game, Player player, int damage) {
		// nothing to do
	}

	@Override
	public void healed(Game game, Player player, int amount) {
		// nothing to do
	}

	@Override
	public void statesChanged(Game game, Player player, List<StateChange> changes) {
		// nothing to do
	}

	@Override
	public void maxLifeChanged(Game game, Player player, int amount) {
		// nothing to do
	}

}
