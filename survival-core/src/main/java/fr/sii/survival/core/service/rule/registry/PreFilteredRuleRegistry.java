package fr.sii.survival.core.service.rule.registry;

import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class PreFilteredRuleRegistry<R extends Rule> implements RuleRegistry<R> {
	private static final Logger LOG = LoggerFactory.getLogger(PreFilteredRuleRegistry.class);

	private RuleRegistry<R> delegate;

	private Predicate<R> predicate;

	public PreFilteredRuleRegistry(Predicate<R> predicate, RuleRegistry<R> delegate) {
		super();
		this.predicate = predicate;
		this.delegate = delegate;
	}

	@Override
	public void register(R rule) {
		if (predicate.test(rule)) {
			delegate.register(rule);
		} else {
			LOG.info("Rule {} rejected", rule.getClass().getSimpleName());
		}
	}

	@Override
	public List<R> getRules() {
		return delegate.getRules();
	}

	@Override
	public void reset() {
		delegate.reset();
	}

}
