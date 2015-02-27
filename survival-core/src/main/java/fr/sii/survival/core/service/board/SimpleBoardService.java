package fr.sii.survival.core.service.board;

import java.util.List;

import fr.sii.survival.core.domain.Board;
import fr.sii.survival.core.domain.Cell;
import fr.sii.survival.core.domain.player.Player;

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
	
	public SimpleBoardService(int rows, int cols, CellProvider cellProvider) {
		super();
		board = new Board(rows, cols);
		this.cellProvider = cellProvider;
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
		return cell;
	}

	@Override
	public Cell add(Player player) {
		Cell cell = cellProvider.getCell(board);
		board.add(player, cell);
		return cell;
	}

	@Override
	public Cell remove(Player player) {
		Cell cell = board.getCell(player);
		if(cell!=null) {
			board.remove(player, cell);
		}
		return cell;
	}

}
