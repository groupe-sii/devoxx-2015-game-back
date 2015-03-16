package fr.sii.survival.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.WebSocketConfig;
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
import fr.sii.survival.core.ext.registry.PreFilteredRegistry;
import fr.sii.survival.core.ext.registry.predicate.TypePredicate;
import fr.sii.survival.core.helper.MultiGameHelper;
import fr.sii.survival.core.listener.game.GameListenerManager;
import fr.sii.survival.core.listener.game.SimpleGameListenerManager;
import fr.sii.survival.core.service.action.ActionService;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.game.GameService;
import fr.sii.survival.core.service.game.SimpleGameService;
import fr.sii.survival.core.service.message.MessageService;

@Configuration
public class GameConfiguration {
	public static final String GAME_MAPPING_PREFIX = WebSocketConfig.SERVER_MAPPING_PREFIX;

	@Autowired
	BoardService boardService;
	
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
		return new SimpleGameService(gameOptions.getMaxPlayers(), gameOptions.getSchedulingDelay(), boardService, messageService, gameListenerManager(), extensionProvider(), gameHelper);
	}

	@Bean
	public GameListenerManager gameListenerManager() {
		return new SimpleGameListenerManager(messageService, extensionService);
	}

	@Bean
	public ExtensionProvider extensionProvider() {
		// provider for basic enemies
		ExtensionProvider basicRandomProvider = new RandomProvider(actionService, extensionService, new PreFilteredRegistry(new TypePredicate(SpecialEnemy.class).negate(), extensionRegistry()));
		// provider for special enemies
		ExtensionProvider specialRandomProvider = new RandomProvider(actionService, extensionService, new PreFilteredRegistry(new TypePredicate(SpecialEnemy.class), extensionRegistry()));
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
