package fr.sii.survival.core.listener.game;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;

public abstract class AbstractSingleGameListener implements GameListener {

	private Game game;
	
	public AbstractSingleGameListener(Game game) {
		super();
		this.game = game;
	}

	@Override
	public void started(Game game) {
		if(this.game.equals(game)) {
			started();
		}
	}

	@Override
	public void stopped(Game game) {
		if(this.game.equals(game)) {
			stopped();
		}
	}

	@Override
	public void joined(Player player, Game game) {
		if(this.game.equals(game)) {
			joined(player);
		}
	}

	@Override
	public void left(Player player, Game game) {
		if(this.game.equals(game)) {
			left(player);
		}
	}

	protected abstract void started();
	
	protected abstract void stopped();
	
	protected abstract void joined(Player player);
	
	protected abstract void left(Player player);
}
