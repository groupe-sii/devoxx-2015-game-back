package fr.sii.survival.core.service.board;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.helper.MultiGameHelper;
import fr.sii.survival.core.listener.board.BoardListener;
import fr.sii.survival.core.listener.board.BoardListenerManager;

/**
 * Simple board service that allows all players to be anywhere on the board.
 * There is no collision detection at all.
 * 
 * @author aurelien
 *
 */
public class SimpleBoardService implements BoardService {
	private static final Logger logger = LoggerFactory.getLogger(SimpleBoardService.class);

	/**
	 * A cell provider use to place new players on the board
	 */
	private CellProvider cellProvider;

	/**
	 * The registry for board listeners
	 */
	private BoardListenerManager listenerManager;

	/**
	 * The number of rows on every board
	 */
	private int rows;

	/**
	 * The number of columns on every board
	 */
	private int cols;

	/**
	 * Utility for storing game
	 */
	private MultiGameHelper gameHelper;

	public SimpleBoardService(int rows, int cols, CellProvider cellProvider, BoardListenerManager listenerManager, MultiGameHelper gameHelper) {
		super();
		this.rows = rows;
		this.cols = cols;
		this.cellProvider = cellProvider;
		this.listenerManager = listenerManager;
		this.gameHelper = gameHelper;
	}

	@Override
	public Board create() {
		return new Board(rows, cols);
	}

	@Override
	public List<Player> getPlayers(Board board, Cell cell) {
		List<Player> players = new ArrayList<>(board.getPlayers(cell));
		logger.debug("players found on {} : {}", cell, players);
		return players;
	}

	@Override
	public Cell move(Board board, Player player, Cell cell) throws GameException {
		Cell old = board.getCell(player);
		if (old != null) {
			board.remove(player, old);
		}
		board.add(player, cell);
		logger.debug("player {} moved from {} to {}", player, old, cell);
		listenerManager.triggerMoved(gameHelper.getGame(board), player, old, cell);
		return cell;
	}

	@Override
	public Cell move(Board board, Player player, Direction direction) throws GameException {
		Cell current = getCell(board, player);
		int x = current.getX() + direction.getX();
		int y = current.getY() + direction.getY();
		if (x >= 0 && x < board.getWidth() && y >= 0 && y < board.getHeight()) {
			return move(board, player, new Cell(x, y));
		} else {
			return current;
		}
	}

	@Override
	public Cell add(Board board, Player player) throws GameException {
		Cell cell = cellProvider.getCell(board);
		board.add(player, cell);
		listenerManager.triggerAdded(gameHelper.getGame(board), player, cell);
		return cell;
	}

	@Override
	public Cell remove(Board board, Player player) throws GameException {
		Cell cell = board.getCell(player);
		if (cell != null) {
			board.remove(player, cell);
		}
		listenerManager.triggerRemoved(gameHelper.getGame(board), player, cell);
		return cell;
	}

	@Override
	public Cell getCell(Board board, Player player) {
		Cell cell = board.getCell(player);
		logger.debug("player {} is on {}", player, cell);
		return cell;
	}

	@Override
	public void addBoardListener(BoardListener listener) {
		listenerManager.addBoardListener(listener);
	}

	@Override
	public void removeBoardListener(BoardListener listener) {
		listenerManager.removeBoardListener(listener);
	}

}
