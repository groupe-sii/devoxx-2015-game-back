package fr.sii.survival.core.ext.behavior.action;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.util.ConcurrentHelper;

/**
 * Action manager that execute an action after a delay. The action is provided by a
 * delegate action manager
 * 
 * @author Aurélien Baudet
 *
 */
public class DelayedActionBehavior implements EnemyActionBehavior {
	private static final Logger LOG = LoggerFactory.getLogger(DelayedActionBehavior.class);

	/**
	 * The action manager to execute
	 */
	private final EnemyActionBehavior delegate;

	/**
	 * The delay in milliseconds
	 */
	private final long delay;

	public DelayedActionBehavior(EnemyActionBehavior delegate, int delay, TimeUnit unit) {
		this(delegate, TimeUnit.MILLISECONDS.convert(delay, unit));
	}
	
	public DelayedActionBehavior(EnemyActionBehavior delegate, long delay) {
		super();
		this.delegate = delegate;
		this.delay = delay;
	}


	@Override
	public void execute(Game game, Cell cell) throws GameException {
		Timer timer = ConcurrentHelper.getGameFactory(game).getTimer("DelayedAction");
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					delegate.execute(game, cell);
				} catch (GameException e) {
					// TODO: execute method should throw an exception to propagate it like other
					LOG.error("Failed to execute delayed action", e);
				}
			}
		}, delay);
	}
}
