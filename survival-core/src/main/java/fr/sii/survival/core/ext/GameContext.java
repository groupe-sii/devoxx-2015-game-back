package fr.sii.survival.core.ext;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;

public class GameContext {
	/**
	 * The current game information
	 */
	private Game game;
	
	/**
	 * The current board information
	 */
	private Board board;
	
	/**
	 * The current cell where your enemy is
	 */
	private Cell cell;

	public GameContext(Game game, Board board, Cell cell) {
		super();
		this.game = game;
		this.board = board;
		this.cell = cell;
	}

	public Game getGame() {
		return game;
	}

	public Board getBoard() {
		return board;
	}

	public Cell getCell() {
		return cell;
	}
}
