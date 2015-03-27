package fr.sii.survival.core.service.action.rule.registry;

import fr.sii.survival.core.service.action.rule.AllowActionRule;
import fr.sii.survival.core.service.rule.registry.RuleRegistry;

/**
 * Specialization that stores the rules that can prevent some actions under some
 * conditions.
 * 
 * @author Aurélien Baudet
 *
 */
public interface AllowActionRuleRegistry extends RuleRegistry<AllowActionRule> {

}
