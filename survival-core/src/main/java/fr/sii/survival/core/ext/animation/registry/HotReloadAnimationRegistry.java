package fr.sii.survival.core.ext.animation.registry;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.ext.animation.AnimationProvider;
import fr.sii.survival.core.reload.ReloadWatcher;

/**
 * An animation registry that listen to reload events. When a reload event is
 * triggered, this registry asks to the auto-discovery registry to reload the
 * animations from the class path.
 * 
 * @author Aurélien Baudet
 *
 */
public class HotReloadAnimationRegistry implements AnimationRegistry {
	private static final Logger LOG = LoggerFactory.getLogger(HotReloadAnimationRegistry.class);

	/**
	 * The auto-discovery registry to reload
	 */
	private AutoDiscoveryAnimationRegistry delegate;

	public HotReloadAnimationRegistry(ReloadWatcher watcher, AutoDiscoveryAnimationRegistry delegate) {
		super();
		this.delegate = delegate;
		watcher.addReloadListener(() -> reload());
	}

	public void reload() {
		LOG.info("Change detected, reloading animations");
		delegate.register();
	}

	@Override
	public void register(AnimationProvider provider) {
		delegate.register(provider);
	}

	@Override
	public List<AnimationProvider> getProviders() {
		return delegate.getProviders();
	}

	@Override
	public void reset() {
		delegate.reset();
	}

}
