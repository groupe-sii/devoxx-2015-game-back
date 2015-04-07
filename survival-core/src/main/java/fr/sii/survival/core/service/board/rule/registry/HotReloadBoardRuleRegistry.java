package fr.sii.survival.core.service.board.rule.registry;

import fr.sii.survival.core.reload.ReloadWatcher;
import fr.sii.survival.core.service.board.rule.AllowMoveRule;
import fr.sii.survival.core.service.rule.registry.AutoDiscoveryRuleRegistry;
import fr.sii.survival.core.service.rule.registry.HotReloadRuleRegistry;

/**
 * Specialization of hot reload rule registry for move rules. See
 * {@link HotReloadRuleRegistry} for more information.
 * 
 * @author Aurélien Baudet
 * @see HotReloadRuleRegistry
 */
public class HotReloadBoardRuleRegistry extends HotReloadRuleRegistry<AllowMoveRule> implements AllowMoveRuleRegistry {

	public HotReloadBoardRuleRegistry(ReloadWatcher watcher, AutoDiscoveryRuleRegistry<AllowMoveRule> delegate) {
		super(watcher, delegate);
	}

}
