package fr.sii.survival.core.ext;

import fr.sii.survival.core.domain.image.Image;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.domain.player.SimpleEnemy;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.player.PlayerService;

/**
 * Base class for enemy extensions
 * 
 * @author Aurélien Baudet
 *
 */
public abstract class EnemyExtension {
	/**
	 * The service used to execute actions
	 */
	protected ActionService actionService;

	/**
	 * The service used to manage board
	 */
	protected BoardService boardService;

	/**
	 * The service used to manage board
	 */
	protected PlayerService playerService;

	/**
	 * The enemy the extension is managing
	 */
	protected Enemy enemy;

	protected EnemyExtension(Enemy enemy) {
		super();
		this.enemy = enemy;
	}

	protected EnemyExtension(String name, Image avatar, int life) {
		this(new SimpleEnemy(name, avatar, life));
	}

	/**
	 * Initialize the extension
	 */
	public abstract void init();

	/**
	 * Run the extension
	 * 
	 * @param context
	 *            the context of the game (game, players, board)
	 * @throws GameException
	 *             when the extension failed to run
	 */
	public abstract void run(GameContext context) throws GameException;

	public Enemy getEnemy() {
		return enemy;
	}

	protected ActionService getActionService() {
		return actionService;
	}

	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}

	protected BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public PlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}
}
