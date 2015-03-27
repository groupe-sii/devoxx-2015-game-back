package fr.sii.survival.core.ext.animation.registry;

import java.util.List;

import fr.sii.survival.core.ext.animation.AnimationProvider;

public interface AnimationRegistry {
	/**
	 * Register a provider
	 * 
	 * @param provider
	 *            the provider to register
	 */
	public void register(AnimationProvider provider);

	/**
	 * Give the whole list of available animation providers
	 * 
	 * @return the list of available animation providers
	 */
	public List<AnimationProvider> getProviders();
}
