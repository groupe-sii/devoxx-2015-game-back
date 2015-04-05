package fr.sii.survival.core.service.board.rule.registry;

import java.util.function.Predicate;

import fr.sii.survival.core.service.board.rule.AllowMoveRule;
import fr.sii.survival.core.service.rule.registry.PreFilteredRuleRegistry;
import fr.sii.survival.core.service.rule.registry.RuleRegistry;

/**
 * Specialization for {@link AllowMoveRule} registry. See
 * {@link PreFilteredRuleRegistry} for more information.
 * 
 * @author Aurélien Baudet
 * @see PreFilteredRuleRegistry
 *
 */
public class PreFilteredMoveRuleRegistry extends PreFilteredRuleRegistry<AllowMoveRule> implements AllowMoveRuleRegistry {

	public PreFilteredMoveRuleRegistry(Predicate<AllowMoveRule> predicate, RuleRegistry<AllowMoveRule> delegate) {
		super(predicate, delegate);
	}

}
