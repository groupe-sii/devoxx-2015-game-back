package fr.sii.survival.core.service.action.rule.registry;

import java.util.List;

import fr.sii.survival.core.service.action.rule.AllowActionRule;
import fr.sii.survival.core.service.rule.registry.SimpleRuleRegistry;

/**
 * Specialization for {@link AllowActionRule} registry. See
 * {@link SimpleRuleRegistry} for more information.
 * 
 * @author Aurélien Baudet
 * @see SimpleRuleRegistry
 */
public class SimpleActionRuleRegistry extends SimpleRuleRegistry<AllowActionRule> implements AllowActionRuleRegistry {

	public SimpleActionRuleRegistry(AllowActionRule... rules) {
		super(rules);
	}

	public SimpleActionRuleRegistry(List<AllowActionRule> rules) {
		super(rules);
	}

}
