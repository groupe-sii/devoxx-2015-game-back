package fr.sii.survival.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.core.service.game.GameService;

@Configuration
public class GameConfiguration {
	@Bean
	public GameService gameService() {
		// TODO: use real game service
		return new GameService() {
		};
	}
}
