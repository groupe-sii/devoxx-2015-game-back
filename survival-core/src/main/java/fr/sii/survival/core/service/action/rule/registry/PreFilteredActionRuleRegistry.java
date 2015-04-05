package fr.sii.survival.core.service.action.rule.registry;

import java.util.function.Predicate;

import fr.sii.survival.core.service.action.rule.AllowActionRule;
import fr.sii.survival.core.service.rule.registry.PreFilteredRuleRegistry;
import fr.sii.survival.core.service.rule.registry.RuleRegistry;

/**
 * Specialization for {@link AllowActionRule} registry. See
 * {@link PreFilteredRuleRegistry} for more information.
 * 
 * @author Aurélien Baudet
 * @see PreFilteredRuleRegistry
 */
public class PreFilteredActionRuleRegistry extends PreFilteredRuleRegistry<AllowActionRule> implements AllowActionRuleRegistry {

	public PreFilteredActionRuleRegistry(Predicate<AllowActionRule> predicate, RuleRegistry<AllowActionRule> delegate) {
		super(predicate, delegate);
	}

}
