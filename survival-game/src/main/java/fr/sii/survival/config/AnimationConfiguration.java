package fr.sii.survival.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.WebSocketConfig;
import fr.sii.survival.core.ext.animation.registry.AnimationRegistry;
import fr.sii.survival.core.ext.animation.registry.AutoDiscoveryAnimationRegistry;
import fr.sii.survival.core.service.animation.AnimationService;
import fr.sii.survival.core.service.animation.SimpleAnimationService;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;

@Configuration
public class AnimationConfiguration {
	public static final String ANIMATION_MAPPING_PREFIX = WebSocketConfig.SERVER_MAPPING_PREFIX+"/animation";

	@Autowired
	MessageService messageService;
	
	@Autowired
	ExtensionService extensionService;
	
	@Bean
	public AnimationService animationService() {
		return new SimpleAnimationService(animationRegistry(), messageService, extensionService);
	}
	
	@Bean
	public AnimationRegistry animationRegistry() {
		return new AutoDiscoveryAnimationRegistry(extensionService);
	}
}
