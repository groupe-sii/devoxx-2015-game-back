package fr.sii.survival.core.service.board.rule.registry;

import java.util.function.Predicate;

import fr.sii.survival.core.service.board.rule.AllowMoveRule;
import fr.sii.survival.core.service.rule.registry.PostFilteredRuleRegistry;
import fr.sii.survival.core.service.rule.registry.RuleRegistry;

/**
 * Specialization for {@link AllowMoveRule} registry. See
 * {@link PostFilteredRuleRegistry} for more information.
 * 
 * @author Aurélien Baudet
 * @see PostFilteredRuleRegistry
 */
public class PostFilteredMoveRuleRegistry extends PostFilteredRuleRegistry<AllowMoveRule> implements AllowMoveRuleRegistry {

	public PostFilteredMoveRuleRegistry(Predicate<AllowMoveRule> predicate, RuleRegistry<AllowMoveRule> delegate) {
		super(predicate, delegate);
	}

}
