package fr.sii.survival.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.player.Player;

public class Game {
	private static int counter = 0;

	/**
	 * The game id
	 */
	private final String id;

	/**
	 * The list of players currently in the game
	 */
	private final List<Player> players;

	/**
	 * The associated board that contains player positions
	 */
	private final Board board;

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
		this.id = "game-" + (counter++);
		this.players = players;
		this.board = board;
		started = false;
	}

	/**
	 * Get the whole list of players. This is a copy of the list of players that
	 * is immutable.
	 * 
	 * @return the whole list of players
	 */
	public List<Player> getPlayers() {
		return getReadOnlyPlayers();
	}

	/**
	 * Get filtered list of players
	 * 
	 * @param predicate
	 *            the filter condition
	 * @return the filtered list of players
	 */
	public List<Player> getPlayers(Predicate<Player> predicate) {
		List<Player> filtered = new ArrayList<>(players.size());
		for (Player p : getReadOnlyPlayers()) {
			if (predicate.test(p)) {
				filtered.add(p);
			}
		}
		return filtered;
	}

	/**
	 * Get the first single player that matches the provided condition
	 * 
	 * @param predicate
	 *            the filter condition
	 * @return the found player that matches the condition or null if no player
	 *         matches
	 */
	public Player getPlayer(Predicate<Player> predicate) {
		List<Player> filtered = getPlayers(predicate);
		return filtered.isEmpty() ? null : filtered.get(0);
	}

	/**
	 * Get the board information
	 * 
	 * @return the board information
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
		return getReadOnlyPlayers().stream()
							.map(p -> p.getId())
							.anyMatch(id -> id.equals(player.getId()));
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
		return getReadOnlyPlayers().stream()
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
		return getReadOnlyPlayers().stream()
							.filter(p -> p.getId().equals(playerId))
							.findFirst()
							.orElse(null);
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
		return new HashCodeBuilder().append(id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof Game)) {
			return false;
		}
		Game other = (Game) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("(id=").append(id).append(",players=").append(players.size()).append(",started=").append(started).append(",full=").append(full).append(")");
		return builder.toString();
	}

	private List<Player> getReadOnlyPlayers() {
		return new CopyOnWriteArrayList<>(players);
	}

}
