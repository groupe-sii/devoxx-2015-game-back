package fr.sii.survival.core.service.board.rules;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.service.board.Direction;

/**
 * Prevent player to move if the player is dead
 * 
 * @author aurelien
 *
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class NoMoveIfDied implements AllowMoveRule {

	@Override
	public boolean isMoveAllowed(Board board, Player player, Cell cell) {
		return isAlive(player);
	}

	@Override
	public boolean isMoveAllowed(Board board, Player player, Direction direction) {
		return isAlive(player);
	}

	private boolean isAlive(Player player) {
		return player.getLife().getCurrent()>0;
	}

}
