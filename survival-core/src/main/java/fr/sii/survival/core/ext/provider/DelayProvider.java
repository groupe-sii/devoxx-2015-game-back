package fr.sii.survival.core.ext.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.exception.ExtensionInitializationException;
import fr.sii.survival.core.ext.EnemyExtension;

/**
 * Provider that execute the delegate only after a certain delay
 * 
 * @author aurelien
 *
 */
public class DelayProvider implements ExtensionProvider {

	private ExtensionProvider delegate;
	
	private long last;

	private long delay;

	public DelayProvider(long delayMs, ExtensionProvider delegate) {
		super();
		this.delegate = delegate;
		this.last = 0;
		this.delay = delayMs;
	}

	public DelayProvider(ExtensionProvider delegate, long delay, TimeUnit unit) {
		this(TimeUnit.MILLISECONDS.convert(delay, unit), delegate);
	}

	@Override
	public List<EnemyExtension> getEnemies(Game game) throws ExtensionInitializationException {
		long now = System.currentTimeMillis();
		if(now - last > delay) {
			last = now;
			return delegate.getEnemies(game);
		} else {
			return new ArrayList<>(0);
		}
	}

}
