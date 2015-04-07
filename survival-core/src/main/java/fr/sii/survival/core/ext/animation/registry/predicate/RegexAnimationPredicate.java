package fr.sii.survival.core.ext.animation.registry.predicate;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import fr.sii.survival.core.ext.animation.AnimationProvider;
import fr.sii.survival.core.ext.registry.predicate.RegexPredicate;

/**
 * A predicate that checks of the {@link AnimationProvider} class matches the
 * provided pattern
 * 
 * @author Aurélien Baudet
 *
 */
public class RegexAnimationPredicate implements Predicate<AnimationProvider> {

	private RegexPredicate delegate;

	public RegexAnimationPredicate(Pattern pattern) {
		this(new RegexPredicate(pattern));
	}

	public RegexAnimationPredicate(String regex) {
		this(new RegexPredicate(regex));
	}

	public RegexAnimationPredicate(String regex, int flags) {
		this(new RegexPredicate(regex, flags));
	}

	public RegexAnimationPredicate(RegexPredicate delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public boolean test(AnimationProvider t) {
		return delegate.test(t.getClass());
	}

}
