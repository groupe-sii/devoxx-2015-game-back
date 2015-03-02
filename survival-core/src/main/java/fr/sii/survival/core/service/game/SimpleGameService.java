package fr.sii.survival.core.service.game;

import java.util.ArrayList;
import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.GameMessage;
import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.service.board.BoardService;

public class SimpleGameService implements GameService {

	/**
	 * 
	 */
	private int remainingSpace = 0;
	
	/**
	 * 
	 */
	private Game game;
	
	/**
	 * 
	 * @param board
	 */
	public SimpleGameService(Board board) {
		this(new Game(board));
	}
	
	public SimpleGameService(Game game) {
		super();
		this.game = game;
	}

	public GameMessage gameMessage(GameMessage message) throws Exception{
		Thread.sleep(3000); // simulated delay
        return new GameMessage(message.getAction(), message.getData());
	}
	
	@Override
	public Board newPlayer(Player player) throws Exception{
		if (remainingSpace == 0){
			//TODO que peux faire Spring pour moi ?
			BoardService newBoard = new BoardService() {
				
				@Override
				public Cell move(Player player, Cell cell) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public List<Player> getPlayers(Cell cell) {
					// TODO Auto-generated method stub
					return null;
				}
			};

			boards.add(newBoard);
			// add player to newBoard
			remainingSpace += 9;
		}else {
			int boardSize = boards.size();
			boolean affected = false;
			for (int i = 0; ! affected &&  i < boardSize; i++){
				BoardService board = boards.get(i);
				if (board.toString() == ""){//place ?
					//add player to board
					affected = true;
					remainingSpace -= 1;
				}
			}
				
		}
			
        return game.getBoard();
	}
	
	@Override
	public void removePlayer(Player player) throws Exception{
        //new GameMessage(message.getAction(), message.getData());
	}
}
