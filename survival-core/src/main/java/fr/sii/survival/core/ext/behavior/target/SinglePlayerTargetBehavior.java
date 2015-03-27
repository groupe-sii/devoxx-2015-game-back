package fr.sii.survival.core.ext.behavior.target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.ext.GameContext;

/**
 * Manager that targets a single player. Until the player is not updated, this
 * target manager will always provide the current cell where the player is.
 * 
 * @author Aurélien Baudet
 *
 */
public class SinglePlayerTargetBehavior implements TargetBehavior {

	private Player player;

	public SinglePlayerTargetBehavior(Player player) {
		super();
		this.player = player;
	}

	@Override
	public List<Cell> getTargetPositions(GameContext context) {
		Cell cell = context.getBoard().getCell(player);
		return cell == null ? new ArrayList<>() : Arrays.asList(cell);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
