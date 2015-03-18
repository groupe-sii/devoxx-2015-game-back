package fr.sii.survival.core.service.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleRuleRegistry<R> implements RuleRegistry<R> {

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

}
