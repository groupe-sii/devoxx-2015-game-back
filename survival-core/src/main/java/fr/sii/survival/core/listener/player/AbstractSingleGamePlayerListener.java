package fr.sii.survival.core.listener.player;

import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;

public abstract class AbstractSingleGamePlayerListener implements PlayerListener {

	private Game game;
	
	public AbstractSingleGamePlayerListener(Game game) {
		super();
		this.game = game;
	}

	@Override
	public void dead(Game game, Player player) {
		if(this.game.equals(game)) {
			dead(player);
		}
	}

	@Override
	public void revived(Game game, Player player) {
		if(this.game.equals(game)) {
			revived(player);
		}
	}

	@Override
	public void hit(Game game, Player player, int damage) {
		if(this.game.equals(game)) {
			hit(player, damage);
		}
	}

	@Override
	public void healed(Game game, Player player, int amount) {
		if(this.game.equals(game)) {
			healed(player, amount);
		}
	}

	@Override
	public void statesChanged(Game game, Player player, List<StateChange> changes) {
		if(this.game.equals(game)) {
			statesChanged(player, changes);
		}
	}

	@Override
	public void maxLifeChanged(Game game, Player player, int amount) {
		if(this.game.equals(game)) {
			maxLifeChanged(player, amount);
		}
	}

	protected abstract void dead(Player player);

	protected abstract void revived(Player player);

	protected abstract void hit(Player player, int damage);

	protected abstract void healed(Player player, int amount);

	protected abstract void statesChanged(Player player, List<StateChange> changes);

	protected abstract void maxLifeChanged(Player player, int amount);
}
