package fr.sii.survival.core.ext.registry.predicate;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class RegexPredicate implements Predicate<Class<?>> {

	private Pattern pattern;
	
	public RegexPredicate(String regex) {
		this(regex, 0);
	}
	
	public RegexPredicate(String regex, int flags) {
		this(Pattern.compile(regex, flags));
	}
	
	public RegexPredicate(Pattern pattern) {
		super();
		this.pattern = pattern;
	}

	@Override
	public boolean test(Class<?> t) {
		return pattern.matcher(t.getSimpleName()).find();
	}

}
