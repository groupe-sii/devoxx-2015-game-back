package fr.sii.survival.core.ext.animation.registry;

import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.ext.animation.AnimationProvider;

/**
 * Registry that filters the animations according to the provided predicate.
 * The filter is applied when registering an animation. The delegate registry
 * will only contain accepted animation. Retrieving the list of animations
 * directly gives delegate list.
 * 
 * @author Aurélien Baudet
 *
 */
public class PreFilteredAnimationRegistry implements AnimationRegistry {
	private static final Logger LOG = LoggerFactory.getLogger(PreFilteredAnimationRegistry.class);

	private AnimationRegistry delegate;
	
	private Predicate<AnimationProvider> predicate;
	
	public PreFilteredAnimationRegistry(Predicate<AnimationProvider> predicate, AnimationRegistry delegate) {
		super();
		this.predicate = predicate;
		this.delegate = delegate;
	}

	@Override
	public void register(AnimationProvider provider) {
		if(predicate.test(provider)) {
			delegate.register(provider);
		} else {
			LOG.info("Animation {} rejected", provider.getClass().getSimpleName());
		}
	}

	@Override
	public List<AnimationProvider> getProviders() {
		return delegate.getProviders();
	}

	@Override
	public void reset() {
		delegate.reset();
	}


}
