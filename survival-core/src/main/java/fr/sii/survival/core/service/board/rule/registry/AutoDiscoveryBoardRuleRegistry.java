package fr.sii.survival.core.service.board.rule.registry;

import fr.sii.survival.core.service.board.rule.AllowMoveRule;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.rule.registry.AutoDiscoveryRuleRegistry;

/**
 * Specialization for {@link AllowMoveRule} registry. See
 * {@link AutoDiscoveryRuleRegistry} for more information.
 * 
 * @author Aurélien Baudet
 * @see AutoDiscoveryRuleRegistry
 */
public class AutoDiscoveryBoardRuleRegistry extends AutoDiscoveryRuleRegistry<AllowMoveRule> implements AllowMoveRuleRegistry {

	public AutoDiscoveryBoardRuleRegistry(AllowMoveRuleRegistry delegate, ExtensionService extensionService, String... packageNames) {
		super(delegate, extensionService, AllowMoveRule.class, packageNames);
	}

}
