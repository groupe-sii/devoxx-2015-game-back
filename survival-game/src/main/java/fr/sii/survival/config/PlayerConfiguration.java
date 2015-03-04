package fr.sii.survival.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.google.common.collect.Ranges;

import fr.sii.survival.WebSocketConfig;
import fr.sii.survival.core.listener.player.PlayerListenerManager;
import fr.sii.survival.core.listener.player.SimplePlayerListenerManager;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;
import fr.sii.survival.core.service.player.PlayerService;
import fr.sii.survival.core.service.player.SimplePlayerService;

@Configuration
@Component
public class PlayerConfiguration {
	public static final String PLAYER_PUBLISH_PREFIX = WebSocketConfig.SERVER_PUBLISH_PREFIX+"/player";
	public static final String PLAYER_MAPPING_PREFIX = WebSocketConfig.SERVER_PUBLISH_PREFIX+"/player";

	@Autowired
	MessageService errorService;
	
	@Autowired
	ExtensionService extensionService;
	
	@Autowired
	BoardService boardService;
	
	@Bean
	public PlayerService playerService(@Value("${game.life.max.lower}") int min, @Value("${game.life.max.upper}") int max) {
		return new SimplePlayerService(Ranges.closed(min, max<=0 ? Integer.MAX_VALUE : max), playerListenerManager());
	}

	@Bean
	public PlayerListenerManager playerListenerManager() {
		return new SimplePlayerListenerManager(errorService, extensionService);
	}
}
