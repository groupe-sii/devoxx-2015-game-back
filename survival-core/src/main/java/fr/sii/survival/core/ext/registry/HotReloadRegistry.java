package fr.sii.survival.core.ext.registry;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.reload.ReloadWatcher;

/**
 * An enemy registry that listen to reload events. When a reload event is
 * triggered, this registry asks to the auto-discovery registry to reload the
 * enemies from the class path.
 * 
 * @author Aurélien Baudet
 *
 */
public class HotReloadRegistry implements EnemyRegistry {
	private static final Logger LOG = LoggerFactory.getLogger(HotReloadRegistry.class);

	/**
	 * The auto-discovery registry to reload
	 */
	private AutoDiscoveryEnemyRegistry delegate;

	public HotReloadRegistry(ReloadWatcher watcher, AutoDiscoveryEnemyRegistry delegate) {
		super();
		this.delegate = delegate;
		watcher.addReloadListener(() -> reload());
	}

	public void reload() {
		LOG.info("Change detected, reloading extensions");
		delegate.register();
	}

	@Override
	public void register(Class<EnemyExtension> extension) {
		delegate.register(extension);
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
