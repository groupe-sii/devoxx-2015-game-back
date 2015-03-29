package fr.sii.survival.core.util;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedExecutionRunnable implements Runnable {
	private final AtomicInteger runCount = new AtomicInteger();
	private final Runnable delegate;
	private volatile ScheduledFuture<?> future;
	private final int numExecutions;

	public FixedExecutionRunnable(Runnable delegate, int numExecutions) {
		this.delegate = delegate;
		this.numExecutions = numExecutions;
	}

	@Override
	public void run() {
		delegate.run();
		if (runCount.incrementAndGet() == numExecutions) {
			future.cancel(false);
		}
	}

	public void run(ScheduledExecutorService executor, long period, TimeUnit unit) {
		future = executor.scheduleAtFixedRate(this, 0, period, unit);
	}
}