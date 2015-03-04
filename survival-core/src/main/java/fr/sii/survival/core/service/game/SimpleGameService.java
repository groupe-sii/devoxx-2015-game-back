package fr.sii.survival.core.service.game;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.AlreadyInGameException;
import fr.sii.survival.core.exception.FullGameException;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.service.board.BoardService;

public class SimpleGameService implements GameService {

	/**
	 * The game data
	 */
	private Game game;

	/**
	 * The board service used to add/remove players on it
	 */
	private BoardService boardService;

	/**
	 * The maximum number of allowed players
	 */
	private int maxPlayers;

	/**
	 * Initialize the service
	 * 
	 * @param maxPlayers
	 *            the maximum number of allowed players on the game
	 * @param boardService
	 *            the board service used to add/remove players on it
	 */
	public SimpleGameService(int maxPlayers, BoardService boardService) {
		this(maxPlayers, new Game(boardService.getBoard()), boardService);
	}

	public SimpleGameService(int maxPlayers, Game game, BoardService boardService) {
		super();
		this.maxPlayers = maxPlayers;
		this.boardService = boardService;
		this.game = game;
	}

	@Override
	public void join(Player player) throws GameException {
		// check if number of players has not reached the max
		if(game.getPlayers().size()>=maxPlayers) {
			throw new FullGameException("The player can't join this game because the game is full");
		}
		// check if player is already in the game
		if(game.contains(player)) {
			throw new AlreadyInGameException("The player can't join this game because he has already joined this game");
		}
		game.addPlayer(player);
		boardService.add(player);
	}

	@Override
	public void quit(Player player) {
		if(game.contains(player)) {
			game.removePlayer(player);
			boardService.remove(player);
		}
	}

	@Override
	public void start() {
		// TODO start running the game and execute enemy extensions
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getPlayer(String playerId) {
		return game.getPlayer(playerId);
	}
}
