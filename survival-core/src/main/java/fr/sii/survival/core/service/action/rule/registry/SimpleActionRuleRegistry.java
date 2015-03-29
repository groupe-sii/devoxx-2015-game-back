package fr.sii.survival.core.service.action.rule.registry;

import java.util.List;

import fr.sii.survival.core.service.action.rule.AllowActionRule;
import fr.sii.survival.core.service.rule.registry.SimpleRuleRegistry;

public class SimpleActionRuleRegistry extends SimpleRuleRegistry<AllowActionRule> implements AllowActionRuleRegistry {

	public SimpleActionRuleRegistry(AllowActionRule... rules) {
		super(rules);
	}

	public SimpleActionRuleRegistry(List<AllowActionRule> rules) {
		super(rules);
	}

}
