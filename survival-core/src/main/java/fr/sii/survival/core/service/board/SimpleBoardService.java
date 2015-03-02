package fr.sii.survival.core.service.board;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
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
	private static Logger logger = LoggerFactory.getLogger(SimpleBoardService.class);

	/**
	 * The board to update
	 */
	private Board board;
	
	/**
	 * A cell provider use to place new players on the board
	 */
	private CellProvider cellProvider;
	
	/**
	 * The registry for board listeners
	 */
	private BoardListenerManager listenerManager;
	
	public SimpleBoardService(int rows, int cols, CellProvider cellProvider, BoardListenerManager listenerManager) {
		this(new Board(rows, cols), cellProvider, listenerManager);
	}
	
	public SimpleBoardService(Board board, CellProvider cellProvider, BoardListenerManager listenerManager) {
		super();
		this.board = board;
		this.cellProvider = cellProvider;
		this.listenerManager = listenerManager;
	}

	@Override
	public List<Player> getPlayers(Cell cell) {
		List<Player> players = board.getPlayers(cell);
		logger.info("players found on {} : {}", cell, players);
		return players;
	}

	@Override
	public Cell move(Player player, Cell cell) {
		Cell old = board.getCell(player);
		if(old!=null) {
			board.remove(player, old);
		}
		board.add(player, cell);
		listenerManager.triggerMoved(player, old, cell);
		return cell;
	}

	@Override
	public Cell add(Player player) {
		Cell cell = cellProvider.getCell(board);
		board.add(player, cell);
		listenerManager.triggerAdded(player, cell);
		return cell;
	}

	@Override
	public Cell remove(Player player) {
		Cell cell = board.getCell(player);
		if(cell!=null) {
			board.remove(player, cell);
		}
		listenerManager.triggerRemoved(player, cell);
		return cell;
	}

	@Override
	public Cell getCell(Player player) {
		Cell cell = board.getCell(player);
		logger.info("player {} is on {}", player, cell);
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

	@Override
	public int getHeight() {
		return board.getHeight();
	}

	@Override
	public int getWidth() {
		return board.getWidth();
	}

	@Override
	public Board getBoard() {
		return board;
	}

}
