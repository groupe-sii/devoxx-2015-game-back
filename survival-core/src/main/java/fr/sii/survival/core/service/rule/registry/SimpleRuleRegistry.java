package fr.sii.survival.core.service.rule.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.sii.survival.core.service.rule.Rule;

/**
 * A simple registry for rules that stores them in memory
 * 
 * @author Aurélien Baudet
 *
 * @param <R>
 *            The type of the registered rules
 */
public class SimpleRuleRegistry<R extends Rule> implements RuleRegistry<R> {

	private List<R> rules;

	public SimpleRuleRegistry(R... rules) {
		this(new ArrayList<>(Arrays.asList(rules)));
	}

	public SimpleRuleRegistry(List<R> rules) {
		super();
		this.rules = rules;
	}

	@Override
	public void register(R rule) {
		rules.add(rule);
	}

	@Override
	public List<R> getRules() {
		return rules;
	}

	@Override
	public void reset() {
		rules.clear();
	}

}
