package fr.sii.survival.core.ext.behavior.action;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.util.FixedExecutionRunnable;
import fr.sii.survival.core.util.ConcurrentHelper;

/**
 * Action manager that execute n times an action. The action is provided by a
 * delegate action manager. The user can specify the number of executions and
 * can also specify the execution rate.
 * 
 * @author Aurélien Baudet
 *
 */
public class RepeatedActionBehavior implements EnemyActionBehavior {
	private static final Logger LOG = LoggerFactory.getLogger(RepeatedActionBehavior.class);

	/**
	 * The action manager to execute
	 */
	private final EnemyActionBehavior delegate;

	/**
	 * The rate in milliseconds
	 */
	private final long rate;

	/**
	 * The number of executions of the action
	 */
	private final int numExecutions;

	public RepeatedActionBehavior(EnemyActionBehavior delegate, long rate, int numExecutions) {
		super();
		this.delegate = delegate;
		this.rate = rate;
		this.numExecutions = numExecutions;
	}

	@Override
	public void execute(Game game, Cell cell) throws GameException {
		LOG.debug("start repeated action {} (game: {}, cell: {})", delegate, game, cell);
		new FixedExecutionRunnable(new DelegateRunnable(game, cell), numExecutions).run(ConcurrentHelper.getGameFactory(game).getScheduledService("RepeatedAction"), rate, TimeUnit.MILLISECONDS);
	}

	private class DelegateRunnable implements Runnable {
		private Game game;
		private Cell cell;

		public DelegateRunnable(Game game, Cell cell) {
			super();
			this.game = game;
			this.cell = cell;
		}

		@Override
		public void run() {
			try {
				delegate.execute(game, cell);
			} catch (GameException e) {
				LOG.error("Failed to execute repeated action", e);
			}
		}

	}
}
