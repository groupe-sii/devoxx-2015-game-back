package fr.sii.survival.core.domain.board;

import java.util.List;

import fr.sii.survival.core.domain.player.Player;

public class CellWithPlayers {
	/**
	 * The cell
	 */
	private final Cell cell;
	
	/**
	 *  The list of players that are on the cell
	 */
	private final List<Player> players;

	public CellWithPlayers(Cell cell, List<Player> players) {
		super();
		this.cell = cell;
		this.players = players;
	}

	public Cell getCell() {
		return cell;
	}

	public List<Player> getPlayers() {
		return players;
	}
}
