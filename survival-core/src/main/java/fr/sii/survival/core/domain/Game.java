package fr.sii.survival.core.domain;

import java.util.ArrayList;
import java.util.List;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.player.Player;

public class Game {
	private List<Player> players;

	private List<Board> boards;
	private Board board;
	
	public List<Player> getPlayers() {
		return players;
	}

	public List<Board> getBoards() {
		return boards;
	}

	public Board getBoard() {
		return board;
	}

	public Game(Board board) {
		this(new ArrayList<>(), board);
	}
	
	public Game(List<Board> boards, Board board) {
		this(new ArrayList<>(), boards, board);
	}
	
	public Game(List<Player> players, List<Board> boards, Board board) {
		super();
		this.players = players;
		this.boards = boards;
		this.board = board;
	}
	
	
}
