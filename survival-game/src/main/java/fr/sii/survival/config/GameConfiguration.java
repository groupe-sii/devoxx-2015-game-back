package fr.sii.survival.config;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.config.options.EnemyOptions;
import fr.sii.survival.config.options.GameOptions;
import fr.sii.survival.core.ext.SpecialEnemy;
import fr.sii.survival.core.ext.provider.DelayProvider;
import fr.sii.survival.core.ext.provider.EveryEnemyProvider;
import fr.sii.survival.core.ext.provider.ExtensionProvider;
import fr.sii.survival.core.ext.provider.MaxProvider;
import fr.sii.survival.core.ext.provider.RandomProvider;
import fr.sii.survival.core.ext.registry.AutoDiscoveryExtensionRegistry;
import fr.sii.survival.core.ext.registry.ExtensionRegistry;
import fr.sii.survival.core.ext.registry.PostFilteredRegistry;
import fr.sii.survival.core.ext.registry.predicate.RegexPredicate;
import fr.sii.survival.core.ext.registry.predicate.TypePredicate;
import fr.sii.survival.core.listener.game.GameListenerManager;
import fr.sii.survival.core.listener.game.SimpleGameListenerManager;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.game.GameRunner;
import fr.sii.survival.core.service.game.GameSelector;
import fr.sii.survival.core.service.game.GameService;
import fr.sii.survival.core.service.game.SimpleGameRunner;
import fr.sii.survival.core.service.game.SimpleGameSelector;
import fr.sii.survival.core.service.game.SimpleGameService;
import fr.sii.survival.core.service.message.MessageService;
import fr.sii.survival.core.service.player.PlayerService;
import fr.sii.survival.core.util.MultiGameHelper;

@Configuration
public class GameConfiguration {
	public static final String GAME_MAPPING_PREFIX = WebSocketConfig.SERVER_MAPPING_PREFIX;

	@Autowired
	BoardService boardService;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	ExtensionService extensionService;
	
	@Autowired
	ActionService actionService;
	
	@Autowired
	EnemyOptions enemyOptions;
	
	@Autowired
	GameOptions gameOptions;
	
	@Autowired
	MultiGameHelper gameHelper;
	
	@Bean
	public GameService gameService() {
		return new SimpleGameService(gameOptions.getMaxPlayers(), gameOptions.getSchedulingDelay(), boardService, gameListenerManager(), gameHelper, gameSelector(), gameRunner());
	}

	@Bean
	public GameRunner gameRunner() {
		return new SimpleGameRunner(boardService, messageService, extensionProvider());
	}

	@Bean
	public GameSelector gameSelector() {
		return new SimpleGameSelector();
	}

	@Bean
	public GameListenerManager gameListenerManager() {
		return new SimpleGameListenerManager(messageService, extensionService);
	}

	@Bean
	public ExtensionProvider extensionProvider() {
		// transform list of exclusion patterns into a list of RegexPredicate. Then the list of predicates is combined using a Or operator
		Predicate<Class<?>> excludeFilter = enemyOptions.getExcludes().stream()
									.<Predicate<Class<?>>>map(exclude -> new RegexPredicate(exclude))
									.reduce(Predicate::or)
									.orElse(p -> false);
		// basic enemy filter -> if predicate returns true, then enemy is added to the registry
		Predicate<Class<?>> basicEnemyFilter = new TypePredicate(SpecialEnemy.class).negate().and(excludeFilter.negate());
		// special enemy filter -> if predicate returns true, then enemy is added to the registry
		Predicate<Class<?>> specialEnemyFilter = new TypePredicate(SpecialEnemy.class).and(excludeFilter.negate());
		// provider for basic enemies
		ExtensionProvider basicRandomProvider = new RandomProvider(actionService, boardService, playerService, extensionService, new PostFilteredRegistry(basicEnemyFilter, extensionRegistry()));
		// provider for special enemies
		ExtensionProvider specialRandomProvider = new RandomProvider(actionService, boardService, playerService, extensionService, new PostFilteredRegistry(specialEnemyFilter, extensionRegistry()));
		// invoke special enemies every n basic enemies
		ExtensionProvider everyProvider = new EveryEnemyProvider(enemyOptions.getSpecialEvery(), basicRandomProvider, specialRandomProvider);
		// limit the maximum number of enemies on the board
		ExtensionProvider maxProvider = new MaxProvider(enemyOptions.getMaxEnemies(), everyProvider);
		// invoke an enemy only after a certain delay
		return new DelayProvider(enemyOptions.getAddDelay(), maxProvider);
	}

	@Bean
	public ExtensionRegistry extensionRegistry() {
		return new AutoDiscoveryExtensionRegistry(extensionService);
	}
}
