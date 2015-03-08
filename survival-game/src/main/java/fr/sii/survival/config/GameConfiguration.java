package fr.sii.survival.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import fr.sii.survival.WebSocketConfig;
import fr.sii.survival.core.helper.MultiGameHelper;
import fr.sii.survival.core.listener.game.GameListenerManager;
import fr.sii.survival.core.listener.game.SimpleGameListenerManager;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.game.GameService;
import fr.sii.survival.core.service.game.SimpleGameService;
import fr.sii.survival.core.service.message.MessageService;

@Configuration
@Component
public class GameConfiguration {
	public static final String GAME_PUBLISH_PREFIX = WebSocketConfig.SERVER_PUBLISH_PREFIX;
	public static final String GAME_MAPPING_PREFIX = WebSocketConfig.SERVER_MAPPING_PREFIX+"/game";

	@Autowired
	BoardService boardService;
	
	@Autowired
	MessageService errorService;
	
	@Autowired
	ExtensionService extensionService;
	
	@Bean
	public MultiGameHelper multiGameHelper() {
		return new MultiGameHelper();
	}
	
	@Bean
	public GameService gameService(@Value("${game.players.max}") int maxPlayers) {
		return new SimpleGameService(maxPlayers, boardService, gameListenerManager(), multiGameHelper());
	}

	@Bean
	public GameListenerManager gameListenerManager() {
		return new SimpleGameListenerManager(errorService, extensionService);
	}
}
