package fr.sii.survival.core.service.game;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.GameMessage;
import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.service.board.BoardService;

public class SimpleGameService implements GameService {

	/**
	 * 
	 */
	private Game game;
	
	/**
	 * 
	 */
	private BoardService boardService;
	
	/**
	 * 
	 * @param board
	 */
	public SimpleGameService(BoardService boardService) {
		this(new Game(boardService.getBoard()), boardService);
	}
	
	public SimpleGameService(Game game, BoardService boardService) {
		super();
		this.boardService = boardService;
		this.game = game;
	}

	public GameMessage gameMessage(GameMessage message) throws Exception{
		Thread.sleep(3000); // simulated delay
        return new GameMessage(message.getAction(), message.getData());
	}
	
	@Override
	public Board newPlayer(Player player) throws Exception{
		boardService.add(player);
						
        return boardService.getBoard();
	}
	
	@Override
	public void removePlayer(Player player) throws Exception{
		boardService.remove(player);
	}
}
