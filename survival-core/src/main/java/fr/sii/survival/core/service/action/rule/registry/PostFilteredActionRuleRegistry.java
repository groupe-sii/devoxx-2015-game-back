package fr.sii.survival.core.service.action.rule.registry;

import java.util.function.Predicate;

import fr.sii.survival.core.service.action.rule.AllowActionRule;
import fr.sii.survival.core.service.rule.registry.PostFilteredRegistry;
import fr.sii.survival.core.service.rule.registry.RuleRegistry;

public class PostFilteredActionRuleRegistry extends PostFilteredRegistry<AllowActionRule> implements AllowActionRuleRegistry {

	public PostFilteredActionRuleRegistry(Predicate<AllowActionRule> predicate, RuleRegistry<AllowActionRule> delegate) {
		super(predicate, delegate);
	}

}
