package fr.sii.survival.core.ext.animation.registry;

import java.lang.reflect.Modifier;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.ext.animation.AnimationProvider;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.util.AutoDiscoveryUtil;

public class AutoDiscoveryAnimationRegistry extends SimpleAnimationRegistry {
	private static final Logger LOG = LoggerFactory.getLogger(AutoDiscoveryAnimationRegistry.class);

	public AutoDiscoveryAnimationRegistry(ExtensionService extensionService) {
		this(extensionService, "fr.sii.survival.ext.animation");
	}

	public AutoDiscoveryAnimationRegistry(ExtensionService extensionService, String... packageNames) {
		super(AutoDiscoveryUtil.find(AnimationProvider.class, new AnimationLoaderProvider(extensionService), packageNames));
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
