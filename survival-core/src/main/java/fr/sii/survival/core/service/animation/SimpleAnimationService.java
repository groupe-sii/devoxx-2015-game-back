package fr.sii.survival.core.service.animation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.animation.Animation;
import fr.sii.survival.core.exception.AnimationInitializationException;
import fr.sii.survival.core.exception.ExtensionException;
import fr.sii.survival.core.ext.animation.AnimationProvider;
import fr.sii.survival.core.ext.animation.registry.AnimationRegistry;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;

/**
 * Simple animation service that delegates the search of animation to {@link AnimationRegistry}.
 * 
 * @author Aurélien Baudet
 *
 */
public class SimpleAnimationService implements AnimationService {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleAnimationService.class);

	private AnimationRegistry registry;
	
	private MessageService messageService;
	
	private ExtensionService extensionService;
	
	public SimpleAnimationService(AnimationRegistry registry, MessageService messageService, ExtensionService extensionService) {
		super();
		this.registry = registry;
		this.messageService = messageService;
		this.extensionService = extensionService;
	}

	@Override
	public List<Animation> getAvailableAnimations() {
		List<AnimationProvider> providers = registry.getProviders();
		List<Animation> animations = new ArrayList<>(providers.size());
		for(AnimationProvider provider : providers) {
			try {
				animations.add(provider.provide());
			} catch (AnimationInitializationException e) {
				LOG.error("Failed to provide animation", e);
				messageService.addError(new ExtensionException("Failed to provide animation", extensionService.getDeveloper(provider.getClass()), e));
			}
		}
		return animations;
	}

}
