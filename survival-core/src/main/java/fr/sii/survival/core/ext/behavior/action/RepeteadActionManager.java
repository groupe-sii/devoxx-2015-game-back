package fr.sii.survival.core.ext.behavior.action;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.exception.GameException;

/**
 * Action manager that execute n times an action. The action is provided by a
 * delegate action manager. The user can specify the number of executions and
 * can also specify the execution rate.
 * 
 * @author aurelien
 *
 */
public class RepeteadActionManager implements EnemyActionManager {
	private static Logger logger = LoggerFactory.getLogger(RepeteadActionManager.class);

	/**
	 * The action manager to execute
	 */
	private EnemyActionManager delegate;

	/**
	 * The rate in milliseconds
	 */
	private long rate;

	/**
	 * The number of executions of the action
	 */
	private int numExecutions;
	
	private GameException error;

	public RepeteadActionManager(EnemyActionManager delegate, long rate, int numExecutions) {
		super();
		this.delegate = delegate;
		this.rate = rate;
		this.numExecutions = numExecutions;
	}

	@Override
	public void execute(Cell cell) throws GameException {
		error = null;
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(() -> {
			try {
				delegate.execute(cell);
				if (numExecutions-- <= 0) {
					service.shutdown();
				}
			} catch (GameException e) {
				logger.error("Failed to execute repeated action", e);
				error = e;
				service.shutdown();
			}
		}, 0, rate, TimeUnit.MILLISECONDS);
		try {
			service.awaitTermination(numExecutions*rate+rate, TimeUnit.MILLISECONDS);
			if(error!=null) {
				throw error;
			}
		} catch (InterruptedException e) {
			throw new ActionException("Failed to wait for repeated action termination", e);
		}
	}

}
