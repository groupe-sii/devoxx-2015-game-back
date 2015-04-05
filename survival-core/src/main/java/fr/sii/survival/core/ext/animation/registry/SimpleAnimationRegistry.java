package fr.sii.survival.core.ext.animation.registry;

import java.util.ArrayList;
import java.util.List;

import fr.sii.survival.core.ext.animation.AnimationProvider;

public class SimpleAnimationRegistry implements AnimationRegistry {

	private List<AnimationProvider> providers;
	
	public SimpleAnimationRegistry() {
		this(new ArrayList<>());
	}

	public SimpleAnimationRegistry(List<AnimationProvider> providers) {
		super();
		this.providers = providers;
	}

	@Override
	public void register(AnimationProvider provider) {
		providers.add(provider);
	}

	@Override
	public List<AnimationProvider> getProviders() {
		return providers;
	}

	@Override
	public void reset() {
		providers.clear();
	}

}
