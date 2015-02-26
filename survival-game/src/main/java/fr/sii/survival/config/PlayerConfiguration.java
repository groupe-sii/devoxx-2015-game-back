package fr.sii.survival.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.controller.PlayerController;
import fr.sii.survival.core.listener.player.PlayerListenerManager;
import fr.sii.survival.core.listener.player.SimplePlayerListenerManager;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.error.ErrorService;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.player.PlayerService;
import fr.sii.survival.core.service.player.SimplePlayerService;

@Configuration
public class PlayerConfiguration {
	@Autowired
	ErrorService errorService;
	
	@Autowired
	ExtensionService extensionService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	PlayerController playerController;
	
	@PostConstruct
	public void init() {
		playerService().addPlayerListener(playerController);
	}
	
	@Bean
	public PlayerService playerService() {
		return new SimplePlayerService(playerListenerManager());
	}

	@Bean
	public PlayerListenerManager playerListenerManager() {
		return new SimplePlayerListenerManager(errorService, extensionService);
	}
}
