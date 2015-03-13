package fr.sii.survival.core.service.game;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.AlreadyInGameException;
import fr.sii.survival.core.exception.FullGameException;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.listener.game.GameListener;
import fr.sii.survival.core.listener.game.GameListenerRegistry;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.action.DelegateActionService;
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
	 * The action service used for enemies
	 */
	private ActionService actionService;

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
	 * Initialize the service
	 * 
	 * @param maxPlayers
	 *            the maximum number of allowed players on the game
	 * @param boardService
	 *            the board service used to add/remove players on it
	 */
	public SimpleGameService(int maxPlayers, BoardService boardService, ActionService actionService, GameListenerRegistry listenerRegistry) {
		this(maxPlayers, new Game(boardService.getBoard()), boardService, actionService, listenerRegistry);
	}
	
	public SimpleGameService(int maxPlayers, Game game, BoardService boardService, ActionService actionService, GameListenerRegistry listenerRegistry) {
		super();
		this.maxPlayers = maxPlayers;
		this.boardService = boardService;
		this.actionService = actionService;
		this.game = game;
		this.listenerRegistry = listenerRegistry;
	}

	@Override
	public void join(Player player) throws GameException {
		// check if number of players has not reached the max
		if(maxPlayers>0 && game.getPlayers().size()>=maxPlayers) {
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
	public void join(Enemy enemy) throws GameException {
		game.addPlayer(enemy);
		boardService.add(enemy);
	}
	
	@Override
	public void quit(Player player) {
		if(game.contains(player)) {
			game.removePlayer(player);
			boardService.remove(player);
		}
	}
	
	@Override
	public void quit(Enemy enemy) {
		if(game.contains(enemy)) {
			game.removePlayer(enemy);
			boardService.remove(enemy);
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

	@Override
	public void addGameListener(GameListener listener) {
		listenerRegistry.addGameListener(listener);
	}

	@Override
	public void removeGameListener(GameListener listener) {
		listenerRegistry.removeGameListener(listener);
	}
}
