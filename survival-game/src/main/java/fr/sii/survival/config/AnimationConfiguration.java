package fr.sii.survival.config;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.config.options.AnimationOptions;
import fr.sii.survival.core.ext.animation.AnimationProvider;
import fr.sii.survival.core.ext.animation.registry.AnimationRegistry;
import fr.sii.survival.core.ext.animation.registry.AutoDiscoveryAnimationRegistry;
import fr.sii.survival.core.ext.animation.registry.HotReloadAnimationRegistry;
import fr.sii.survival.core.ext.animation.registry.PreFilteredAnimationRegistry;
import fr.sii.survival.core.ext.animation.registry.SimpleAnimationRegistry;
import fr.sii.survival.core.ext.animation.registry.predicate.RegexAnimationPredicate;
import fr.sii.survival.core.reload.ReloadWatcher;
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
	
	@Autowired
	AnimationOptions animationOptions;
	
	@Autowired
	ReloadWatcher reloadWatcher;
	
	@Bean
	public AnimationService animationService() {
		return new SimpleAnimationService(animationRegistry(), messageService, extensionService);
	}
	
	@Bean
	public AnimationRegistry animationRegistry() {
		// transform list of exclusion patterns into a list of RegexPredicate. Then the list of predicates is combined using a Or operator
		Predicate<AnimationProvider> excludeFilter = animationOptions.getExcludes().stream()
									.<Predicate<AnimationProvider>>map(exclude -> new RegexAnimationPredicate(exclude))
									.reduce(Predicate::or)
									.orElse(p -> false);
		return new HotReloadAnimationRegistry(reloadWatcher, new AutoDiscoveryAnimationRegistry(new PreFilteredAnimationRegistry(excludeFilter.negate(), new SimpleAnimationRegistry()), extensionService));
	}
}
