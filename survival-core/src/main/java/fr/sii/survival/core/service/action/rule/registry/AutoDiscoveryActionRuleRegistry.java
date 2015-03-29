package fr.sii.survival.core.service.action.rule.registry;

import fr.sii.survival.core.service.action.rule.AllowActionRule;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.rule.registry.AutoDiscoveryRuleRegistry;

public class AutoDiscoveryActionRuleRegistry extends AutoDiscoveryRuleRegistry<AllowActionRule> implements AllowActionRuleRegistry {

	public AutoDiscoveryActionRuleRegistry(AllowActionRuleRegistry delegate, ExtensionService extensionService, String... packageNames) {
		super(delegate, extensionService, AllowActionRule.class, packageNames);
	}


}
