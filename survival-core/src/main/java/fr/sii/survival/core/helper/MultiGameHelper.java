package fr.sii.survival.core.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.GameNotFoundException;

public class MultiGameHelper {

	/**
	 * Map that stores games by id
	 */
	private Map<String, Game> gamesById;
	
	public MultiGameHelper() {
		this(new HashMap<>());
	}

	public MultiGameHelper(Map<String, Game> gamesById) {
		super();
		this.gamesById = gamesById;
	}

	public Game getGame(String gameId) throws GameNotFoundException {
		if(gameId==null) {
			throw new GameNotFoundException("Id of the game is null");
		}
		Game game = gamesById.get(gameId);
		if(game==null) {
			throw new GameNotFoundException("Game with id "+gameId+" doesn't exist");
		}
		return game;
	}

	public void set(String gameId, Game game) {
		gamesById.put(gameId, game);
	}
	
	public Game getGame(Player player) throws GameNotFoundException {
		if(player==null) {
			throw new GameNotFoundException("Player is null");
		}
		for(Game game : gamesById.values()) {
			if(game.contains(player)) {
				return game;
			}
		}
		throw new GameNotFoundException("No game found for player "+player);
	}

	public Game getGame(Board board) throws GameNotFoundException {
		if(board==null) {
			throw new GameNotFoundException("Board is null");
		}
		for(Game game : gamesById.values()) {
			if(board.equals(game.getBoard())) {
				return game;
			}
		}
		throw new GameNotFoundException("No game found for board "+board);
	}
	
	public List<Game> getGames() {
		return new ArrayList<>(gamesById.values());
	}

}
