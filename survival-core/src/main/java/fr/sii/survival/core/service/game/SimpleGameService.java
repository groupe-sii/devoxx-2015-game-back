package fr.sii.survival.core.service.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.domain.player.Wizard;
import fr.sii.survival.core.exception.AlreadyInGameException;
import fr.sii.survival.core.exception.FullGameException;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.exception.GameNotFoundException;
import fr.sii.survival.core.exception.PlayerNotFoundException;
import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.provider.ExtensionProvider;
import fr.sii.survival.core.helper.MultiGameHelper;
import fr.sii.survival.core.listener.game.GameListener;
import fr.sii.survival.core.listener.game.GameListenerRegistry;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.message.MessageService;


public class SimpleGameService implements GameService {
	private static final Logger logger = LoggerFactory.getLogger(SimpleGameService.class);

	/**
	 * The board service used to add/remove players on it
	 */
	private BoardService boardService;

	/**
	 * The message service
	 */
	private MessageService messageService;

	/**
	 * The maximum number of allowed players
	 */
	private int maxPlayers;
	
	/**
	 * The delay of the scheduling (in milliseconds)
	 */
	private long delay;

	/**
	 * The registry that stores the list of action listeners that will be
	 * triggered when an action is done
	 */
	private GameListenerRegistry listenerRegistry;
	
	/**
	 * Provides the extensions
	 */
	private ExtensionProvider extensionProvider;

	/**
	 * Helper that stores games by id
	 */
	private MultiGameHelper gameHelper;
	
	/**
	 * The game selector
	 */
	private GameSelector gameSelector;
	
	private Map<Game, ScheduledExecutorService> executors;
	
	private List<EnemyExtension> extensions;

	/**
	 * Initialize the service
	 * 
	 * @param maxPlayers
	 *            the maximum number of allowed players on the game
	 * @param delay
	 *            the scheduling delay
	 * @param boardService
	 *            the board service used to add/remove players on it
	 * @param messageService
	 *            the service for messages
	 * @param listenerRegistry
	 *            the registry for game events
	 * @param extensionProvider
	 *            provide enemies to add in the game
	 * @param gameHelper
	 *            helper for useful for multi-game management
	 * @param gameSelector
	 *            game select management
	 */
	public SimpleGameService(int maxPlayers, long delay, BoardService boardService, MessageService messageService, GameListenerRegistry listenerRegistry, ExtensionProvider extensionProvider, MultiGameHelper gameHelper, GameSelector gameSelector) {
		super();
		this.maxPlayers = maxPlayers;
		this.delay = delay;
		this.boardService = boardService;
		this.messageService = messageService;
		this.listenerRegistry = listenerRegistry;
		this.extensionProvider = extensionProvider;
		this.gameHelper = gameHelper;
		this.gameSelector = gameSelector;
		this.extensions = new ArrayList<EnemyExtension>();
	}

	@Override
	public Game create() {
		Game game = new Game(boardService.create());
		gameHelper.set(game.getId(), game);
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executors.put(game, executor);
		return game;
	}
	
	@Override
	public Game select() {
		Game game = gameSelector.select(gameHelper.getGames());
		if(game==null) {
			game = create();
		}
		return game;
	}
	
	@Override
	public void join(Game game, Player player) throws GameException {
		if(player instanceof Wizard) {
			// check if number of players has not reached the max
			if(isFull(game)) {
				throw new FullGameException("The player can't join this game because the game is full");
			}
			// check if player is already in the game
			if(game.contains(player) || game.contains(player.getPlayerInfo().getName())) {
				throw new AlreadyInGameException("The player can't join this game because he has already joined this game");
			}
		}
		game.addPlayer(player);
		if(isFull(game)) {
			game.setFull(true);
		}
		boardService.add(game.getBoard(), player);
	}

	private boolean isFull(Game game) {
		return maxPlayers>0 && game.getPlayers(new PlayerTypePredicate(Wizard.class)).size()>=maxPlayers;
	}

	@Override
	public void quit(Game game, Player player) throws GameException {
		if (game.contains(player)) {
			game.removePlayer(player);
			game.setFull(false);
			boardService.remove(game.getBoard(), player);
		}
	}

	@Override
	public void start(Game game) throws GameException {
		// start running the game and execute enemy extensions
		if(!game.isStarted()) {
			logger.info("Start the game {}", game);
			ScheduledExecutorService executor = executors.get(game);
			executor.scheduleWithFixedDelay(() -> {
				try {
					// TODO: externalize ?
					synchronized(extensions) {
						// add new enemies
						List<EnemyExtension> enemies = extensionProvider.getEnemies(game);
						for(EnemyExtension enemy : enemies) {
							enemy.init();
							join(game, enemy.getEnemy());
						}
						extensions.addAll(enemies);
						// remove dead enemies
						for(Iterator<EnemyExtension> it = extensions.iterator() ; it.hasNext() ; ) {
							EnemyExtension ext = it.next();
							if(ext.getEnemy().getLife().getCurrent()<=0) {
								quit(game, ext.getEnemy());
								it.remove();
							}
						}
						// execute extensions
						System.out.println("============debut============");
						extensions.parallelStream().forEach(extension -> {
							try {
								extension.run(new GameContext(game, new Board(game.getBoard()), boardService.getCell(game.getBoard(), extension.getEnemy())));
							} catch (Exception e) {
								messageService.addError(new GameException("Failed to run extensions", e));
							}
						});
						System.out.println("============fin============");
//						for(EnemyExtension extension : extensions) {
//							extension.run(new GameContext(game, new Board(game.getBoard()), boardService.getCell(extension.getEnemy())));
//						}
					}
				} catch (Exception e) {
					logger.error("Failed to run extensions", e);
					messageService.addError(new GameException("Failed to run extensions", e));
				}
			}, 0L, delay, TimeUnit.MILLISECONDS);
		} else {
			throw new GameException("Game already started");
		}
	}

	@Override
	public void stop(Game game) throws GameException {
		if(game.isStarted()) {
			// TODO: remove all players
			executors.get(game).shutdown();
		} else {
			throw new GameException("Game already stopped");
		}
	}

	@Override
	public boolean isStarted(Game game) {
		return game.isStarted();
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
