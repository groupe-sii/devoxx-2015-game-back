package fr.sii.survival.dto;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;

public class PlayerMoved {
	/**
	 * The player that moved
	 */
	private Player player;

	/**
	 * The old player position on the board. The old cell is null when the
	 * player is added in the board
	 */
	private Cell oldCell;

	/**
	 * The new player position on the board. The new cell is null when the
	 * player is removed from the board
	 */
	private Cell newCell;

	public PlayerMoved(Player player, Cell oldCell, Cell newCell) {
		super();
		this.player = player;
		this.oldCell = oldCell;
		this.newCell = newCell;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Cell getOldCell() {
		return oldCell;
	}

	public void setOldCell(Cell oldCell) {
		this.oldCell = oldCell;
	}

	public Cell getNewCell() {
		return newCell;
	}

	public void setNewCell(Cell newCell) {
		this.newCell = newCell;
	}
}
