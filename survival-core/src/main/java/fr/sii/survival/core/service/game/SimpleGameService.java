package fr.sii.survival.core.service.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.provider.ExtensionProvider;
import fr.sii.survival.core.listener.game.GameListener;
import fr.sii.survival.core.listener.game.GameListenerRegistry;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.message.MessageService;


public class SimpleGameService implements GameService {
	private static final Logger logger = LoggerFactory.getLogger(SimpleGameService.class);

	/**
	 * The game data
	 */
	private Game game;

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

	private boolean started;
	
	private ScheduledExecutorService executor;
	
	private List<EnemyExtension> extensions;
	
	/**
	 * Initialize the service
	 * 
	 * @param maxPlayers
	 *            the maximum number of allowed players on the game
	 * @param boardService
	 *            the board service used to add/remove players on it
	 */
	public SimpleGameService(int maxPlayers, long delay, BoardService boardService, MessageService messageService, GameListenerRegistry listenerRegistry, ExtensionProvider extensionProvider) {
		this(maxPlayers, delay, new Game(boardService.getBoard()), boardService, messageService, listenerRegistry, extensionProvider);
	}
	
	public SimpleGameService(int maxPlayers, long delay, Game game, BoardService boardService, MessageService messageService, GameListenerRegistry listenerRegistry, ExtensionProvider extensionProvider) {
		super();
		this.maxPlayers = maxPlayers;
		this.delay = delay;
		this.boardService = boardService;
		this.messageService = messageService;
		this.game = game;
		this.listenerRegistry = listenerRegistry;
		this.extensionProvider = extensionProvider;
		this.started = false;
		this.extensions = new ArrayList<EnemyExtension>();
	}

	@Override
	public void join(Player player) throws GameException {
		if(player instanceof Wizard) {
			// check if number of players has not reached the max
			if(maxPlayers>0 && game.getPlayers().size()>=maxPlayers) {
				throw new FullGameException("The player can't join this game because the game is full");
			}
			// check if player is already in the game
			if(game.contains(player)) {
				throw new AlreadyInGameException("The player can't join this game because he has already joined this game");
			}
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
	public void start() throws GameException {
		// start running the game and execute enemy extensions
		if(!this.started) {
			logger.info("Start the game");
			executor = Executors.newSingleThreadScheduledExecutor();
			executor.scheduleWithFixedDelay(() -> {
				try {
					// TODO: externalize ?
					synchronized(extensions) {
						// add new enemies
						List<EnemyExtension> enemies = extensionProvider.getEnemies(game);
						for(EnemyExtension enemy : enemies) {
							enemy.init();
							join(enemy.getEnemy());
						}
						extensions.addAll(enemies);
						// remove dead enemies
						for(Iterator<EnemyExtension> it = extensions.iterator() ; it.hasNext() ; ) {
							EnemyExtension ext = it.next();
							if(ext.getEnemy().getLife().getCurrent()<=0) {
								quit(ext.getEnemy());
								it.remove();
							}
						}
						// execute extensions
						System.out.println("============debut============");
						extensions.parallelStream().forEach(extension -> {
							try {
								extension.run(new GameContext(game, new Board(game.getBoard()), boardService.getCell(extension.getEnemy())));
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
	public void stop() throws GameException {
		if(this.started) {
			// TODO: remove all players
			executor.shutdown();
		} else {
			throw new GameException("Game already stopped");
		}
	}

	@Override
	public boolean isStarted() {
		return started;
	}

	@Override
	public Player getPlayer(String playerId) {
		return game.getPlayer(playerId);
	}

	@Override
	public Game getGame() {
		return game;
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
