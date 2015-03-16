package fr.sii.survival.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.WebSocketConfig;
import fr.sii.survival.config.options.LifeOptions;
import fr.sii.survival.core.helper.MultiGameHelper;
import fr.sii.survival.core.listener.player.PlayerListenerManager;
import fr.sii.survival.core.listener.player.SimplePlayerListenerManager;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;
import fr.sii.survival.core.service.player.PlayerService;
import fr.sii.survival.core.service.player.SimplePlayerService;

@Configuration
public class PlayerConfiguration {
	public static final String PLAYER_MAPPING_PREFIX = WebSocketConfig.SERVER_MAPPING_PREFIX+"/player";

	@Autowired
	MessageService messageService;
	
	@Autowired
	ExtensionService extensionService;
	
	@Autowired
	BoardService boardService;

	@Autowired
	MultiGameHelper multiGameHelper;

	@Autowired
	LifeOptions lifeOptions;
	
	@Bean
	public PlayerService playerService() {
		return new SimplePlayerService(lifeOptions.getDefaultLife(), lifeOptions.getMax(), playerListenerManager(), multiGameHelper);
	}

	@Bean
	public PlayerListenerManager playerListenerManager() {
		return new SimplePlayerListenerManager(messageService, extensionService);
	}
}
