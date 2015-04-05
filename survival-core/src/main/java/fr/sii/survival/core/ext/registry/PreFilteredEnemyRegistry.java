package fr.sii.survival.core.ext.registry;

import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.ext.EnemyExtension;

/**
 * Registry that filters the enemies according to the provided predicate.
 * The filter is applied when registering an enemy. The delegate registry
 * will only contain accepted enemies. Retrieving the list of enemies
 * directly gives delegate list.
 * 
 * @author Aurélien Baudet
 *
 */
public class PreFilteredEnemyRegistry implements EnemyRegistry {
	private static final Logger LOG = LoggerFactory.getLogger(PreFilteredEnemyRegistry.class);

	private EnemyRegistry delegate;
	
	private Predicate<Class<?>> predicate;
	
	public PreFilteredEnemyRegistry(Predicate<Class<?>> predicate, EnemyRegistry delegate) {
		super();
		this.predicate = predicate;
		this.delegate = delegate;
	}

	@Override
	public void register(Class<EnemyExtension> extension) {
		if(predicate.test(extension)) {
			delegate.register(extension);
		} else {
			LOG.info("Enemy {} rejected", extension.getSimpleName());
		}
	}

	@Override
	public List<Class<EnemyExtension>> getEnemyExtensions() {
		return delegate.getEnemyExtensions();
	}

	@Override
	public void reset() {
		delegate.reset();
	}

}
