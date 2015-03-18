package fr.sii.survival.core.service.board.rules;

import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.rules.AutoDiscoveryRuleRegistry;

public class AutoDiscoveryBoardRuleRegistry extends AutoDiscoveryRuleRegistry<AllowMoveRule> implements AllowMoveRuleRegistry {

	public AutoDiscoveryBoardRuleRegistry(ExtensionService extensionService, String... packageNames) {
		super(extensionService, AllowMoveRule.class, packageNames);
	}


}
