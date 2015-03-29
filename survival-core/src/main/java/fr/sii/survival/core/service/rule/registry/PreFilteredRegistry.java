package fr.sii.survival.core.service.rule.registry;

import java.util.List;
import java.util.function.Predicate;

import fr.sii.survival.core.service.rule.Rule;

/**
 * Registry that filters the extensions according to the provided predicate. The
 * filter is applied when registering the rule. The delegate registry will
 * contain only accepted rules. Access to rule list is done by the delegate
 * registry
 * 
 * @author Aurélien Baudet
 *
 */
public class PreFilteredRegistry<R extends Rule> implements RuleRegistry<R> {

	private RuleRegistry<R> delegate;

	private Predicate<R> predicate;

	public PreFilteredRegistry(Predicate<R> predicate, RuleRegistry<R> delegate) {
		super();
		this.predicate = predicate;
		this.delegate = delegate;
	}

	@Override
	public void register(R rule) {
		if (predicate.test(rule)) {
			delegate.register(rule);
		}
	}

	@Override
	public List<R> getRules() {
		return delegate.getRules();
	}

}
