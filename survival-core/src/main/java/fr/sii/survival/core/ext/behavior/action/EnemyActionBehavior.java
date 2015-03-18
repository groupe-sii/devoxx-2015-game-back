package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.GameException;

public interface EnemyActionBehavior {
	public void execute(Game game, Cell cell) throws GameException;
}
