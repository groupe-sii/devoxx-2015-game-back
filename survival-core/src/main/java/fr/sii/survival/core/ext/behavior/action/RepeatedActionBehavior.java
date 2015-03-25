package fr.sii.survival.core.ext.behavior.action;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.GameException;

/**
 * Action manager that execute n times an action. The action is provided by a
 * delegate action manager. The user can specify the number of executions and
 * can also specify the execution rate.
 * 
 * @author aurelien
 *
 */
public class RepeatedActionBehavior implements EnemyActionBehavior {
	private static final Logger LOG = LoggerFactory.getLogger(RepeatedActionBehavior.class);

	/**
	 * The action manager to execute
	 */
	private EnemyActionBehavior delegate;

	/**
	 * The rate in milliseconds
	 */
	private long rate;

	/**
	 * The number of executions of the action
	 */
	private int numExecutions;
	
	private int count;
	
	public RepeatedActionBehavior(EnemyActionBehavior delegate, long rate, int numExecutions) {
		super();
		this.delegate = delegate;
		this.rate = rate;
		this.numExecutions = numExecutions;
	}

	@Override
	public void execute(Game game, Cell cell) throws GameException {
		LOG.debug("start repeated action {} (game: {}, cell: {})", delegate, game, cell);
		count = 0;
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(() -> {
			try {
				if(count < numExecutions) {
					LOG.debug("execute repeated action {} (game: {}, cell: {})", delegate, game, cell);
					delegate.execute(game, cell);
				}
				if (count++ > numExecutions) {
					service.shutdown();
				}
			} catch (GameException e) {
				LOG.error("Failed to execute repeated action", e);
				service.shutdown();
			}
		}, 0, rate, TimeUnit.MILLISECONDS);
	}

}
