package fr.sii.survival.core.listener.board;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;

public interface BoardListenerTrigger {
	public void triggerMoved(Player player, Cell oldCell, Cell newCell);
	
	public void triggerAdded(Player player, Cell cell);
	
	public void triggerRemoved(Player player, Cell cell);
}
