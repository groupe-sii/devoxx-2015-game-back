package fr.sii.survival.core.reload;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * A watcher that checks at fixed rate if a reload is needed. The need of reload
 * is delegated to a predicate function.
 * 
 * @author Aurélien Baudet
 *
 */
public class FixedRateWatcher extends BaseReloadWatcher {

	/**
	 * The predicate used to check if reload is needed
	 */
	private final Predicate<?> predicate;

	/**
	 * The rate in milliseconds
	 */
	private final long rate;

	private final Timer timer;

	/**
	 * Initializes a timer that will actively check if a reload is needed. The
	 * timer is immediately started.
	 * 
	 * @param predicate
	 *            the predicate to execute for checking the need of reload
	 * @param rate
	 *            the delay between two checks (in milliseconds)
	 */
	public FixedRateWatcher(Predicate<?> predicate, long rate) {
		this(predicate, rate, true);
	}

	/**
	 * Initializes a timer that will actively check if a reload is needed. The
	 * timer is immediately started.
	 * 
	 * @param predicate
	 *            the predicate to execute for checking the need of reload
	 * @param rate
	 *            the delay between two checks
	 * @param unit
	 *            the time unit for the rate
	 */
	public FixedRateWatcher(Predicate<?> predicate, int rate, TimeUnit unit) {
		this(predicate, rate, unit, true);
	}

	/**
	 * Initializes a timer that will actively check if a reload is needed. The
	 * timer is immediately started only if start parameter is true. If not, you
	 * have to start it manually.
	 * 
	 * @param predicate
	 *            the predicate to execute for checking the need of reload
	 * @param rate
	 *            the delay between two checks (in milliseconds)
	 * @param start
	 *            immediately start the timer if true
	 */
	public FixedRateWatcher(Predicate<?> predicate, long rate, boolean start) {
		super();
		this.predicate = predicate;
		this.rate = rate;
		timer = new Timer("TimerWatcher");
		if (start) {
			start();
		}
	}

	/**
	 * Initializes a timer that will actively check if a reload is needed. The
	 * timer is immediately started only if start parameter is true. If not, you
	 * have to start it manually.
	 * 
	 * @param predicate
	 *            the predicate to execute for checking the need of reload
	 * @param rate
	 *            the delay between two checks
	 * @param unit
	 *            the time unit for the rate
	 * @param start
	 *            immediately start the timer if true
	 */
	public FixedRateWatcher(Predicate<?> predicate, int rate, TimeUnit unit, boolean start) {
		this(predicate, TimeUnit.MILLISECONDS.convert(rate, unit), start);
	}

	@Override
	public void start() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (predicate.test(null)) {
					triggerListeners();
				}
			}
		}, 0, rate);
	}

	@Override
	public void stop() {
		timer.cancel();
	}

}
