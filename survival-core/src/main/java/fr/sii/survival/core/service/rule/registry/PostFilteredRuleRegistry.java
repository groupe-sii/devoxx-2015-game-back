package fr.sii.survival.core.service.rule.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import fr.sii.survival.core.service.rule.Rule;

/**
 * Registry that filters the extensions according to the provided predicate.
 * All rules are registered in the delegate registry.
 * The filter is applied when retrieving a rule.
 * 
 * @author Aurélien Baudet
 *
 */
public class PostFilteredRuleRegistry<R extends Rule> implements RuleRegistry<R> {

	private RuleRegistry<R> delegate;
	
	private Predicate<R> predicate;
	
	public PostFilteredRuleRegistry(Predicate<R> predicate, RuleRegistry<R> delegate) {
		super();
		this.predicate = predicate;
		this.delegate = delegate;
	}

	@Override
	public void register(R rule) {
		delegate.register(rule);
	}

	@Override
	public List<R> getRules() {
		List<R> rules = delegate.getRules();
		List<R> filtered = new ArrayList<>(rules.size());
		for(R rule : rules) {
			if(predicate.test(rule)) {
				filtered.add(rule);
			}
		}
		return filtered;
	}

	@Override
	public void reset() {
		delegate.reset();
	}

}
