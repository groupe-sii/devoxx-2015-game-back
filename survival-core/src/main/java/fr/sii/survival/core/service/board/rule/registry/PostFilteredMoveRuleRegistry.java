package fr.sii.survival.core.service.board.rule.registry;

import java.util.function.Predicate;

import fr.sii.survival.core.service.board.rule.AllowMoveRule;
import fr.sii.survival.core.service.rule.registry.PostFilteredRegistry;
import fr.sii.survival.core.service.rule.registry.RuleRegistry;

/**
 * Specialization for {@link AllowMoveRule} registry. See
 * {@link PostFilteredRegistry} for more information.
 * 
 * @author Aurélien Baudet
 * @see PostFilteredRegistry
 */
public class PostFilteredMoveRuleRegistry extends PostFilteredRegistry<AllowMoveRule> implements AllowMoveRuleRegistry {

	public PostFilteredMoveRuleRegistry(Predicate<AllowMoveRule> predicate, RuleRegistry<AllowMoveRule> delegate) {
		super(predicate, delegate);
	}

}
