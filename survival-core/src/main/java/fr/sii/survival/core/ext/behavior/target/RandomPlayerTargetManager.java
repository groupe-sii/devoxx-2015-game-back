package fr.sii.survival.core.ext.behavior.target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.ext.GameContext;

/**
 * Manager that targets a random player every call.
 * 
 * @author aurelien
 *
 */
public class RandomPlayerTargetManager implements TargetManager {

	@Override
	public List<Cell> getTargetPositions(GameContext context) {
		List<Player> players = context.getGame().getPlayers();
		if(players.size()>0) {
			Player player = players.get((int) Math.floor(Math.random()*players.size()));
			Cell cell = context.getBoard().getCell(player);
			return cell==null ? new ArrayList<>() : Arrays.asList(cell);
		} else {
			return new ArrayList<>();
		}
	}

}
