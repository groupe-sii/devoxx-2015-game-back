package fr.sii.survival.core.service.rule.registry;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.reload.ReloadWatcher;
import fr.sii.survival.core.service.rule.Rule;

/**
 * A rule registry that listen to reload events. When a reload event is
 * triggered, this registry asks to the auto-discovery registry to reload the
 * rules from the class path.
 * 
 * @author Aurélien Baudet
 *
 * @param <R>
 *            The type of the rules
 */
public class HotReloadRuleRegistry<R extends Rule> implements RuleRegistry<R> {
	private static final Logger LOG = LoggerFactory.getLogger(HotReloadRuleRegistry.class);

	/**
	 * The auto-discovery registry to reload
	 */
	private AutoDiscoveryRuleRegistry<R> delegate;

	public HotReloadRuleRegistry(ReloadWatcher watcher, AutoDiscoveryRuleRegistry<R> delegate) {
		super();
		this.delegate = delegate;
		watcher.addReloadListener(() -> reload());
	}

	public void reload() {
		LOG.info("Change detected, reloading rules");
		delegate.register();
	}

	@Override
	public void register(R rule) {
		delegate.register(rule);
	}

	@Override
	public List<R> getRules() {
		return delegate.getRules();
	}

	@Override
	public void reset() {
		delegate.reset();
	}

}
