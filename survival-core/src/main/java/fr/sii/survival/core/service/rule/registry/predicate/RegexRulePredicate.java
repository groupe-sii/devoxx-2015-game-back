package fr.sii.survival.core.service.rule.registry.predicate;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import fr.sii.survival.core.ext.registry.predicate.RegexPredicate;
import fr.sii.survival.core.service.rule.Rule;

public class RegexRulePredicate<R extends Rule> implements Predicate<R> {

	private RegexPredicate delegate;
	
	public RegexRulePredicate(Pattern pattern) {
		this(new RegexPredicate(pattern));
	}
	
	public RegexRulePredicate(String regex) {
		this(new RegexPredicate(regex));
	}
	
	public RegexRulePredicate(String regex, int flags) {
		this(new RegexPredicate(regex, flags));
	}
	
	public RegexRulePredicate(RegexPredicate delegate) {
		super();
		this.delegate = delegate;
	}
	
	@Override
	public boolean test(Rule t) {
		return delegate.test(t.getClass());
	}

}
