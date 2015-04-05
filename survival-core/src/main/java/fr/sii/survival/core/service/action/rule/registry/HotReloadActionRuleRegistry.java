package fr.sii.survival.core.service.action.rule.registry;

import fr.sii.survival.core.reload.ReloadWatcher;
import fr.sii.survival.core.service.action.rule.AllowActionRule;
import fr.sii.survival.core.service.rule.registry.AutoDiscoveryRuleRegistry;
import fr.sii.survival.core.service.rule.registry.HotReloadRuleRegistry;

/**
 * Specialization of hot reload rule registry for action rules. See
 * {@link HotReloadRuleRegistry} for more information.
 * 
 * @author Aurélien Baudet
 * @see HotReloadRuleRegistry
 *
 */
public class HotReloadActionRuleRegistry extends HotReloadRuleRegistry<AllowActionRule> implements AllowActionRuleRegistry {

	public HotReloadActionRuleRegistry(ReloadWatcher watcher, AutoDiscoveryRuleRegistry<AllowActionRule> delegate) {
		super(watcher, delegate);
	}

}
