package fr.sii.survival.core.service.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.provider.ExtensionProvider;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.message.MessageService;

public class SimpleGameRunner implements GameRunner {
	private static final Logger logger = LoggerFactory.getLogger(SimpleGameRunner.class);

	/**
	 * The associated game
	 */
	private Game game;

	/**
	 * The associated game service
	 */
	private GameService gameService;

	/**
	 * The board service used to add/remove players on it
	 */
	private BoardService boardService;

	/**
	 * The message service
	 */
	private MessageService messageService;

	/**
	 * Provides the extensions
	 */
	private ExtensionProvider extensionProvider;

	private List<EnemyExtension> extensions;

	public SimpleGameRunner(BoardService boardService, MessageService messageService, ExtensionProvider extensionProvider) {
		super();
		this.boardService = boardService;
		this.messageService = messageService;
		this.extensionProvider = extensionProvider;
		extensions = new ArrayList<EnemyExtension>();
	}
	
	@Override
	public void run() {
		try {
			// TODO: externalize ?
			synchronized(extensions) {
				// add new enemies
				List<EnemyExtension> enemies = extensionProvider.getEnemies(game);
				for(EnemyExtension enemy : enemies) {
					enemy.init();
					gameService.join(game, enemy.getEnemy());
				}
				extensions.addAll(enemies);
				// remove dead enemies
				for(Iterator<EnemyExtension> it = extensions.iterator() ; it.hasNext() ; ) {
					EnemyExtension ext = it.next();
					if(ext.getEnemy().getLife().getCurrent()<=0) {
						gameService.quit(game, ext.getEnemy());
						it.remove();
					}
				}
				// execute extensions
				logger.debug("============debut============");
				extensions.parallelStream().forEach(extension -> {
					try {
						extension.run(new GameContext(game, game.getBoard(), boardService.getCell(game.getBoard(), extension.getEnemy())));
					} catch (Exception e) {
						messageService.addError(new GameException("Failed to run extensions", e));
					}
				});
//				for(EnemyExtension extension : extensions) {
//					extension.run(new GameContext(game, new Board(game.getBoard()), boardService.getCell(extension.getEnemy())));
//				}
				logger.debug("============fin============");
			}
		} catch (Exception e) {
			logger.error("Failed to run extensions", e);
			messageService.addError(new GameException("Failed to run extensions", e));
		}
	}
	
	@Override
	public void stop() throws GameException {
		// remove all players
		synchronized(extensions) {
			extensions.clear();
			for(Player p : new CopyOnWriteArrayList<>(game.getPlayers())) {
				gameService.quit(game, p);
			}
		}
	}

	@Override
	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
	
	@Override
	public void setGame(Game game) {
		this.game = game;
	}

	public List<EnemyExtension> getExtensions() {
		return extensions;
	}

	@Override
	public Game getGame() {
		return game;
	}
}
