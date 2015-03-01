package fr.sii.survival.core.ext.behavior.action;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import fr.sii.survival.core.domain.board.Cell;

/**
 * Action manager that execute n times an action. The action is provided by a
 * delegate action manager. The user can specify the number of executions and
 * can also specify the execution rate.
 * 
 * @author aurelien
 *
 */
public class RepeteadActionManager implements EnemyActionManager {

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

	public RepeteadActionManager(EnemyActionManager delegate, long rate, int numExecutions) {
		super();
		this.delegate = delegate;
		this.rate = rate;
		this.numExecutions = numExecutions;
	}

	@Override
	public void execute(Cell cell) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				delegate.execute(cell);
				if (numExecutions-- <= 0) {
					service.shutdown();
				}
			}
		}, 0, rate, TimeUnit.MILLISECONDS);
	}

}
