package fr.sii.survival.core.service.board;

import java.util.List;

import fr.sii.survival.core.domain.Board;
import fr.sii.survival.core.domain.Cell;
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
		super();
		board = new Board(rows, cols);
		this.cellProvider = cellProvider;
		this.listenerManager = listenerManager;
	}

	@Override
	public List<Player> getPlayers(Cell cell) {
		return board.getPlayers(cell);
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
	public void addBoardListener(BoardListener listener) {
		listenerManager.addBoardListener(listener);
	}

	@Override
	public void removeBoardListener(BoardListener listener) {
		listenerManager.removeBoardListener(listener);
	}

}
