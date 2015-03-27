package fr.sii.survival.core.service.board.rule;

import java.util.List;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.board.BoardListener;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.board.Direction;
import fr.sii.survival.core.service.board.rule.registry.AllowMoveRuleRegistry;

/**
 * Board service decorator that allows to register rules in order to prevent board moves.
 * 
 * @author Aurélien Baudet
 *
 */
public class DelegateRulesBoardService implements BoardService {
	private BoardService delegate;
	
	private AllowMoveRuleRegistry registry;
	
	public DelegateRulesBoardService(BoardService delegate, AllowMoveRuleRegistry registry) {
		super();
		this.delegate = delegate;
		this.registry = registry;
	}

	public boolean isMoveAllowed(Board board, Player player, Cell cell) {
		for(AllowMoveRule rule : registry.getRules()) {
			if(!rule.isMoveAllowed(board, player, cell)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isMoveAllowed(Board board, Player player, Direction direction) {
		for(AllowMoveRule rule : registry.getRules()) {
			if(!rule.isMoveAllowed(board, player, direction)) {
				return false;
			}
		}
		return true;
	}



	@Override
	public Board create() {
		return delegate.create();
	}

	@Override
	public List<Player> getPlayers(Board board, Cell cell) {
		return delegate.getPlayers(board, cell);
	}

	@Override
	public Cell move(Board board, Player player, Cell cell) throws GameException {
		if(isMoveAllowed(board, player, cell)) {
			return delegate.move(board, player, cell);
		} else {
			return cell;
		}
	}

	@Override
	public Cell move(Board board, Player player, Direction direction) throws GameException {
		if(isMoveAllowed(board, player, direction)) {
			return delegate.move(board, player, direction);
		} else {
			return delegate.getCell(board, player);
		}
	}

	@Override
	public Cell add(Board board, Player player) throws GameException {
		return delegate.add(board, player);
	}

	@Override
	public Cell remove(Board board, Player player) throws GameException {
		return delegate.remove(board, player);
	}

	@Override
	public Cell getCell(Board board, Player player) {
		return delegate.getCell(board, player);
	}
	
	@Override
	public void addBoardListener(BoardListener listener) {
		delegate.addBoardListener(listener);
	}

	@Override
	public void removeBoardListener(BoardListener listener) {
		delegate.removeBoardListener(listener);
	}

	
}
