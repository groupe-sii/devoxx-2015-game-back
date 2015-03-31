package fr.sii.survival.core.ext.animation.registry;

import java.util.List;

import fr.sii.survival.core.ext.animation.AnimationProvider;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.util.AutoDiscoveryInstanceProvider;
import fr.sii.survival.core.util.AutoDiscoveryUtil;

public class AutoDiscoveryAnimationRegistry implements AnimationRegistry {
	private AnimationRegistry delegate;
	
	private ExtensionService extensionService;
	
	private String[] packageNames;

	public AutoDiscoveryAnimationRegistry(AnimationRegistry delegate, ExtensionService extensionService) {
		this(delegate, extensionService, "fr.sii.survival.ext.animation");
	}

	public AutoDiscoveryAnimationRegistry(AnimationRegistry delegate, ExtensionService extensionService, String... packageNames) {
		super();
		this.delegate = delegate;
		this.extensionService = extensionService;
		this.packageNames = packageNames;
		register();
	}
	
	public void register() {
		for(AnimationProvider provider : AutoDiscoveryUtil.find(AnimationProvider.class, new AutoDiscoveryInstanceProvider<>("Animation", extensionService), packageNames)) {
			register(provider);
		}
	}
	
	@Override
	public void register(AnimationProvider provider) {
		delegate.register(provider);
	}

	@Override
	public List<AnimationProvider> getProviders() {
		return delegate.getProviders();
	}

}
