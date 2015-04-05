package fr.sii.survival.core.service.board.rule.registry;

import java.util.List;

import fr.sii.survival.core.service.board.rule.AllowMoveRule;
import fr.sii.survival.core.service.rule.registry.SimpleRuleRegistry;

/**
 * Specialization for {@link AllowMoveRule} registry. See
 * {@link SimpleRuleRegistry} for more information
 * 
 * @author Aurélien Baudet
 * @see SimpleRuleRegistry
 *
 */
public class SimpleMoveRuleRegistry extends SimpleRuleRegistry<AllowMoveRule> implements AllowMoveRuleRegistry {

	public SimpleMoveRuleRegistry(AllowMoveRule... rules) {
		super(rules);
	}

	public SimpleMoveRuleRegistry(List<AllowMoveRule> rules) {
		super(rules);
	}

}
