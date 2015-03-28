package fr.sii.survival.core.service.board.rule;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.service.board.Direction;

/**
 * Prevent player to move if the player is dead
 * 
 * @author Aurélien Baudet
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class NoMoveIfDeadRule implements AllowMoveRule {

	@Override
	public boolean isMoveAllowed(Board board, Player player, Cell cell) {
		return player.isAlive();
	}

	@Override
	public boolean isMoveAllowed(Board board, Player player, Direction direction) {
		return player.isAlive();
	}

}
