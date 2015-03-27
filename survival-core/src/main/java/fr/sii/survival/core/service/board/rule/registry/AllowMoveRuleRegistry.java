package fr.sii.survival.core.service.board.rule.registry;

import fr.sii.survival.core.service.board.rule.AllowMoveRule;
import fr.sii.survival.core.service.rule.registry.RuleRegistry;

/**
 * Specialization for rules that prevent players to move under some conditions.
 * 
 * @author Aurélien Baudet
 *
 */
public interface AllowMoveRuleRegistry extends RuleRegistry<AllowMoveRule> {
}
