package fr.sii.survival.core.listener.board;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;

public interface BoardListenerTrigger {
	public void triggerMoved(Game game, Player player, Cell oldCell, Cell newCell);
	
	public void triggerAdded(Game game, Player player, Cell cell);
	
	public void triggerRemoved(Game game, Player player, Cell cell);
}
