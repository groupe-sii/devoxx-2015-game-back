package fr.sii.survival.core.service.game;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.AlreadyInGameException;
import fr.sii.survival.core.exception.FullGameException;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.exception.GameNotFoundException;
import fr.sii.survival.core.exception.PlayerNotFoundException;
import fr.sii.survival.core.helper.MultiGameHelper;
import fr.sii.survival.core.listener.game.GameListener;
import fr.sii.survival.core.listener.game.GameListenerRegistry;
import fr.sii.survival.core.service.board.BoardService;

public class SimpleGameService implements GameService {

	/**
	 * The board service used to add/remove players on it
	 */
	private BoardService boardService;

	/**
	 * The maximum number of allowed players
	 */
	private int maxPlayers;

	/**
	 * The registry that stores the list of action listeners that will be
	 * triggered when an action is done
	 */
	private GameListenerRegistry listenerRegistry;

	/**
	 * Helper that stores games by id
	 */
	private MultiGameHelper gameHelper;
	
	/**
	 * Initialize the service
	 * 
	 * @param maxPlayers
	 *            the maximum number of allowed players on the game
	 * @param boardService
	 *            the board service used to add/remove players on it
	 * @param listenerRegistry
	 *            the registry for game events
	 */
	public SimpleGameService(int maxPlayers, BoardService boardService, GameListenerRegistry listenerRegistry, MultiGameHelper gameHelper) {
		super();
		this.maxPlayers = maxPlayers;
		this.boardService = boardService;
		this.listenerRegistry = listenerRegistry;
		this.gameHelper = gameHelper;
	}

	@Override
	public Game create() {
		Game game = new Game(boardService.create());
		gameHelper.set(game.getId(), game);
		return game;
	}
	
	@Override
	public void join(Game game, Player player) throws GameException {
		// check if number of players has not reached the max
		if (maxPlayers > 0 && game.getPlayers().size() >= maxPlayers) {
			throw new FullGameException("The player can't join this game because the game is full");
		}
		// check if player is already in the game
		if (game.contains(player) || game.contains(player.getPlayerInfo().getName())) {
			throw new AlreadyInGameException("The player can't join this game because he has already joined this game");
		}
		game.addPlayer(player);
		boardService.add(game.getBoard(), player);
	}

	@Override
	public void quit(Game game, Player player) throws GameException {
		if (game.contains(player)) {
			game.removePlayer(player);
			boardService.remove(game.getBoard(), player);
		}
	}

	@Override
	public void start(Game game) {
		// TODO start running the game and execute enemy extensions

	}

	@Override
	public void stop(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public Player getPlayer(Game game, String playerId) throws PlayerNotFoundException {
		if(playerId==null) {
			throw new PlayerNotFoundException("Id of the player is null");
		}
		Player player = game.getPlayer(playerId);
		if(player==null) {
			throw new PlayerNotFoundException("Player with id "+playerId+" doesn't exist");
		}
		return player;
	}

	@Override
	public Game getGame(String gameId) throws GameNotFoundException {
		return gameHelper.getGame(gameId);
	}

	@Override
	public void addGameListener(GameListener listener) {
		listenerRegistry.addGameListener(listener);
	}

	@Override
	public void removeGameListener(GameListener listener) {
		listenerRegistry.removeGameListener(listener);
	}
}
