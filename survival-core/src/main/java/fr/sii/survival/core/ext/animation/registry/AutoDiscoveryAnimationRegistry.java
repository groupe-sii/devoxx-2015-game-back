package fr.sii.survival.core.ext.animation.registry;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.ext.animation.AnimationProvider;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.util.AutoDiscoveryUtil;

public class AutoDiscoveryAnimationRegistry implements AnimationRegistry {
	private static final Logger LOG = LoggerFactory.getLogger(AutoDiscoveryAnimationRegistry.class);
	
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
		for(AnimationProvider provider : AutoDiscoveryUtil.find(AnimationProvider.class, new AnimationLoaderProvider(extensionService), packageNames)) {
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
	
	private static final class AnimationLoaderProvider implements Function<Class<? extends AnimationProvider>, AnimationProvider> {
		private ExtensionService extensionService;

		public AnimationLoaderProvider(ExtensionService extensionService) {
			super();
			this.extensionService = extensionService;
		}

		@Override
		public AnimationProvider apply(Class<? extends AnimationProvider> type) {
			if (!type.isInterface() && !Modifier.isAbstract(type.getModifiers())) {
				try {
					LOG.info("found animation {} created by {}", type.getName(), extensionService.getDeveloper(type));
					return type.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					LOG.error("cannot instantiate animation {} created by {}. Cause: {}", type.getName(), extensionService.getDeveloper(type), e);
				}
			}
			return null;
		}
		
	}

}
