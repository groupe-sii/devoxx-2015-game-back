package fr.sii.survival.core.domain.board;

import java.util.List;

import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.util.MultiValueMatrix;

public class Board {
	private static int counter = 0;
	
	private MultiValueMatrix<Player> matrix;
	
	/**
	 * The id of the board
	 */
	private int id;

	/**
	 * Copy constructor that copies the board information
	 * 
	 * @param board
	 *            the board to copy
	 */
	public Board(Board board) {
		this(board.getId(), board.getWidth(), board.getHeight());
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				List<Player> players = board.getPlayers(new Cell(i, j));
				getPlayers(new Cell(i, j)).addAll(players);
			}
		}
	}

	/**
	 * Initialize a new board with provided number of rows and number of columns
	 * 
	 * @param rows
	 *            the number of rows
	 * @param cols
	 *            the number of columns
	 */
	public Board(int rows, int cols) {
		this(counter++, rows, cols);
	}
	
	protected Board(int id, int rows, int cols) {
		this.id = id;
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

	public int getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return matrix.toString();
	}
}
