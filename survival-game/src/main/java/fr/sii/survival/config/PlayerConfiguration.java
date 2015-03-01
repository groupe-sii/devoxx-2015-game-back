package fr.sii.survival.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Ranges;

import fr.sii.survival.WebSocketConfig;
import fr.sii.survival.controller.PlayerController;
import fr.sii.survival.core.listener.player.PlayerListenerManager;
import fr.sii.survival.core.listener.player.SimplePlayerListenerManager;
import fr.sii.survival.core.log.LogPlayerListener;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;
import fr.sii.survival.core.service.player.PlayerService;
import fr.sii.survival.core.service.player.SimplePlayerService;

@Configuration
public class PlayerConfiguration {
	public static final String PLAYER_PUBLISH_PREFIX = WebSocketConfig.SERVER_PUBLISH_PREFIX+"/player";

	@Autowired
	MessageService errorService;
	
	@Autowired
	ExtensionService extensionService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	PlayerController playerController;
	
	@Value("${game.life.max.lower}")
	int minimum;
	
	@Value("${game.life.max.upper}")
	int maximum;
	
	@PostConstruct
	public void init() {
		playerService().addPlayerListener(new LogPlayerListener());
		playerService().addPlayerListener(playerController);
	}
	
	@Bean
	public PlayerService playerService() {
		return new SimplePlayerService(Ranges.closed(minimum, maximum<=0 ? Integer.MAX_VALUE : maximum), playerListenerManager());
	}

	@Bean
	public PlayerListenerManager playerListenerManager() {
		return new SimplePlayerListenerManager(errorService, extensionService);
	}
}
