package fr.sii.survival.core.domain;

import java.util.List;

import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.util.MultiValueMatrix;

public class Board {
	private MultiValueMatrix<Player> matrix;

	public Board(int rows, int cols) {
		super();
		matrix = new MultiValueMatrix<Player>(cols, rows);
	}

	/**
	 * Add the player on the provided cell.
	 * 
	 * @param player
	 *            the player to add
	 * @param cell
	 *            the well where to place the player
	 */
	public void add(Player player, Cell cell) {
		matrix.add(cell.getX(), cell.getY(), player);
	}

	/**
	 * Remove the player from the provided cell.
	 * 
	 * @param player
	 *            the player to remove
	 * @param cell
	 *            the cell that contains the player
	 */
	public void remove(Player player, Cell cell) {
		matrix.remove(cell.getX(), cell.getY(), player);
	}

	/**
	 * Get the list of players that are currently on the provided cell.
	 * 
	 * @param cell
	 *            the cell use to get player list
	 * @return the list of players, empty list if there is no player on the cell
	 */
	public List<Player> getPlayers(Cell cell) {
		return matrix.get(cell.getX(), cell.getY());
	}

	/**
	 * Search the position of the player in the game.
	 * 
	 * @param player
	 *            the player to look for
	 * @return the cell if the player is found, null otherwise
	 */
	public Cell getCell(Player player) {
		for (int i = 0; i < matrix.getCols(); i++) {
			for (int j = 0; j < matrix.getRows(); j++) {
				List<Player> players = matrix.get(i, j);
				if (players.contains(player)) {
					return new Cell(i, j);
				}
			}
		}
		return null;
	}

	public int getHeight() {
		return matrix.getRows();
	}

	public int getWidth() {
		return matrix.getCols();
	}
	
	@Override
	public String toString() {
		return matrix.toString();
	}
}
