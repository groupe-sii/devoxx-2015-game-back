package fr.sii.survival.core.service.board.rule.registry;

import java.util.function.Predicate;

import fr.sii.survival.core.service.board.rule.AllowMoveRule;
import fr.sii.survival.core.service.rule.registry.PreFilteredRegistry;
import fr.sii.survival.core.service.rule.registry.RuleRegistry;

public class PreFilteredMoveRuleRegistry extends PreFilteredRegistry<AllowMoveRule> implements AllowMoveRuleRegistry {

	public PreFilteredMoveRuleRegistry(Predicate<AllowMoveRule> predicate, RuleRegistry<AllowMoveRule> delegate) {
		super(predicate, delegate);
	}

}
