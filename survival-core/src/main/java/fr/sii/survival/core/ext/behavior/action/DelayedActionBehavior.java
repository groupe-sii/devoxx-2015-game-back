package fr.sii.survival.core.ext.behavior.action;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.exception.GameException;

/**
 * Action manager that execute an action after a delay. The action is provided by a
 * delegate action manager
 * 
 * @author aurelien
 *
 */
public class DelayedActionBehavior implements EnemyActionBehavior {
	private static Logger logger = LoggerFactory.getLogger(DelayedActionBehavior.class);

	/**
	 * The action manager to execute
	 */
	private EnemyActionBehavior delegate;

	/**
	 * The delay in milliseconds
	 */
	private long delay;

	public DelayedActionBehavior(EnemyActionBehavior delegate, int delay, TimeUnit unit) {
		this(delegate, TimeUnit.MILLISECONDS.convert(delay, unit));
	}
	
	public DelayedActionBehavior(EnemyActionBehavior delegate, long delay) {
		super();
		this.delegate = delegate;
		this.delay = delay;
	}

//	@Override
//	public void execute(Cell cell) throws GameException {
//		try {
//			Thread.sleep(delay);
//			delegate.execute(cell);
//		} catch (InterruptedException e) {
//			// TODO: execute method should throw an exception to propagate it like other
//			logger.error("Failed to execute delayed action", e);
//			throw new ActionException("Failed to execute delayed action", e);
//		}
//	}


	@Override
	public void execute(Game game, Cell cell) throws GameException {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleWithFixedDelay(() -> {
			try {
				delegate.execute(game, cell);
			} catch (GameException e) {
				// TODO: execute method should throw an exception to propagate it like other
				logger.error("Failed to execute delayed action", e);
			} finally {
				service.shutdown();
			}
		}, delay, delay, TimeUnit.MILLISECONDS);
	}
}
