package fr.sii.survival.core.service.action.rule.registry;

import java.util.function.Predicate;

import fr.sii.survival.core.service.action.rule.AllowActionRule;
import fr.sii.survival.core.service.rule.registry.PostFilteredRuleRegistry;
import fr.sii.survival.core.service.rule.registry.RuleRegistry;

/**
 * Specialization for {@link AllowActionRule} registry. See
 * {@link PostFilteredRuleRegistry} for more information.
 * 
 * @author Aurélien Baudet
 * @see PostFilteredRuleRegistry
 */
public class PostFilteredActionRuleRegistry extends PostFilteredRuleRegistry<AllowActionRule> implements AllowActionRuleRegistry {

	public PostFilteredActionRuleRegistry(Predicate<AllowActionRule> predicate, RuleRegistry<AllowActionRule> delegate) {
		super(predicate, delegate);
	}

}
