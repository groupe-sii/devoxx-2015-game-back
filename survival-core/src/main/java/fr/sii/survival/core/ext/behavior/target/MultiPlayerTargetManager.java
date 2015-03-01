package fr.sii.survival.core.ext.behavior.target;

import java.util.ArrayList;
import java.util.List;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.ext.GameContext;

// TODO: manage locks to avoid concurrent modification exception
public class MultiPlayerTargetManager implements TargetManager {

	private List<Player> players;
	
	public MultiPlayerTargetManager() {
		this(new ArrayList<>());
	}

	public MultiPlayerTargetManager(List<Player> players) {
		super();
		this.players = players;
	}

	@Override
	public List<Cell> getTargetPositions(GameContext context) {
		List<Cell> cells = new ArrayList<Cell>(players.size());
		for(Player player : players) {
			Cell cell = context.getBoard().getCell(player);
			cells.add(cell);
		}
		return cells;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public void removePlayer(Player player) {
		players.remove(player);
	}
}
