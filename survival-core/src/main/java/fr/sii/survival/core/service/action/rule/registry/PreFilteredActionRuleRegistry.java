package fr.sii.survival.core.service.action.rule.registry;

import java.util.function.Predicate;

import fr.sii.survival.core.service.action.rule.AllowActionRule;
import fr.sii.survival.core.service.rule.registry.PreFilteredRegistry;
import fr.sii.survival.core.service.rule.registry.RuleRegistry;

public class PreFilteredActionRuleRegistry extends PreFilteredRegistry<AllowActionRule> implements AllowActionRuleRegistry {

	public PreFilteredActionRuleRegistry(Predicate<AllowActionRule> predicate, RuleRegistry<AllowActionRule> delegate) {
		super(predicate, delegate);
	}

}
