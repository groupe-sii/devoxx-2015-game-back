package fr.sii.survival.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.player.Player;

public class Game {
	private static int counter = 0;
	
	/**
	 * The game id
	 */
	private String id;
	
	/**
	 * The list of players currently in the game
	 */
	private List<Player> players;

	/**
	 * The associated board that contains player positions
	 */
	private Board board;

	/**
	 * Is the game started
	 */
	private boolean started;
	
	/**
	 * Is the game full
	 */
	private boolean full;
	
	public Game(Board board) {
		this(new ArrayList<>(), board);
	}

	public Game(List<Player> players, Board board) {
		super();
		this.id = "game-"+(counter++);
		this.players = players;
		this.board = board;
		started = false;
	}

	/**
	 * Get the whole list of players
	 * 
	 * @return the whole list of players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Get filtered list of players
	 * 
	 * @param predicate the filter condition
	 * @return the 
	 */
	public List<Player> getPlayers(Predicate<Player> predicate) {
		List<Player> filtered = new ArrayList<>(players.size());
		for(Player p : players) {
			if(predicate.test(p)) {
				filtered.add(p);
			}
		}
		return filtered;
	}

	/**
	 * Get the board information
	 * 
	 * @return 
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Add a player to the list of players regardless if the player is already
	 * present
	 * 
	 * @param player
	 *            the player to add
	 */
	public void addPlayer(Player player) {
		players.add(player);
	}

	/**
	 * Remove a player from the list of players regardless if the player is
	 * present
	 * 
	 * @param player
	 *            the player to remove
	 */
	public void removePlayer(Player player) {
		players.remove(player);
	}

	/**
	 * Check if the player exists in the list of players. The check is done
	 * using the player id.
	 * 
	 * @param player
	 *            the player to check existence
	 * @return true if the player is present in the list of players, false
	 *         otherwise
	 */
	public boolean contains(Player player) {
		return players.stream()
				.map(p -> p.getId())
				.anyMatch(n -> n.equals(player.getId()));
	}

	/**
	 * Check if the player exists in the list of players. The check is done
	 * using the player name.
	 * 
	 * @param name
	 *            the name to check existence
	 * @return true if the player is present in the list of players, false
	 *         otherwise
	 */
	public boolean contains(String name) {
		return players.stream()
				.map(p -> p.getPlayerInfo().getName())
				.anyMatch(n -> n.equals(name));
	}

	/**
	 * Retrieve a player according to his id
	 * 
	 * @param playerId
	 *            the id of the player
	 * @return the player if found, null otherwise
	 */
	public Player getPlayer(String playerId) {
		return players.stream()
				.filter(p -> p.getId().equals(playerId))
				.findFirst()
				.orElseGet(null);
	}

	public String getId() {
		return id;
	}
	
	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Game other = (Game) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
