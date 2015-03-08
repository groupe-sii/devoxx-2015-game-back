package fr.sii.survival.core.listener.board;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;

public abstract class AbstractSingleGameBoardListener implements BoardListener {

	private Game game;
	
	public AbstractSingleGameBoardListener(Game game) {
		super();
		this.game = game;
	}

	@Override
	public void moved(Game game, Player player, Cell oldCell, Cell newCell) {
		if(this.game.equals(game)) {
			moved(player, oldCell, newCell);
		}
	}

	@Override
	public void added(Game game, Player player, Cell cell) {
		if(this.game.equals(game)) {
			added(player, cell);
		}
	}

	@Override
	public void removed(Game game, Player player, Cell cell) {
		if(this.game.equals(game)) {
			removed(player, cell);
		}
	}
	
	protected abstract void moved(Player player, Cell oldCell, Cell newCell);
	
	protected abstract void added(Player player, Cell cell);
	
	protected abstract void removed(Player player, Cell cell);
}
