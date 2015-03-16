package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.GameException;

public interface EnemyActionManager {
	public void execute(Cell cell) throws GameException;
}
