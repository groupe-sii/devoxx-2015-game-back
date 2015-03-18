package fr.sii.survival.core.service.action.rules;

import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.rules.AutoDiscoveryRuleRegistry;

public class AutoDiscoveryActionRuleRegistry extends AutoDiscoveryRuleRegistry<AllowActionRule> implements AllowActionRuleRegistry {

	public AutoDiscoveryActionRuleRegistry(ExtensionService extensionService, String... packageNames) {
		super(extensionService, AllowActionRule.class, packageNames);
	}


}
