package fr.sii.survival.core.service.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.ExtensionInitializationException;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.provider.ExtensionProvider;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.message.MessageService;
import fr.sii.survival.core.util.ConcurrentHelper;

public class SimpleGameRunner implements GameRunner {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleGameRunner.class);

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
			synchronized(game) {
				// add new enemies
				addEnemies();
				// remove dead enemies
				removeEnemies();
				// execute extensions
				LOG.debug("============debut============");
				for(EnemyExtension extension : extensions) {
					runExtension(extension);
				}
				LOG.debug("============fin============");
			}
		} catch (Exception e) {
			LOG.error("Failed to add or remove enemy on game {}. Cause: {}", game, e);
			messageService.addError(new GameException("Failed to add or remove enemy", e));
		}
	}

	private void addEnemies() throws ExtensionInitializationException, GameException {
		List<EnemyExtension> enemies = extensionProvider.getEnemies(game);
		for(EnemyExtension enemy : enemies) {
			enemy.init();
			gameService.join(game, enemy.getEnemy());
		}
		extensions.addAll(enemies);
	}

	private void removeEnemies() throws GameException {
		for(Iterator<EnemyExtension> it = extensions.iterator() ; it.hasNext() ; ) {
			EnemyExtension ext = it.next();
			if(ext.getEnemy().getLife().getCurrent()<=0) {
				gameService.quit(game, ext.getEnemy());
				it.remove();
			}
		}
	}

	private void runExtension(EnemyExtension extension) throws GameException {
		try {
			extension.run(new GameContext(game, game.getBoard(), boardService.getCell(game.getBoard(), extension.getEnemy())));
		} catch (Exception e) {
			messageService.addError(new GameException("Failed to run extension", e));
		}
	}
	
	@Override
	public void stop() throws GameException {
		// stop all threads that was eventually started by any implementation
		ConcurrentHelper.stop(game);
		// remove all players
		synchronized(extensions) {
			extensions.clear();
			for(Player p : game.getPlayers()) {
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
