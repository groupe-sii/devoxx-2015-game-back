package fr.sii.survival.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.game.GameService;
import fr.sii.survival.core.service.game.SimpleGameService;

@Configuration
public class GameConfiguration {
	@Autowired
	BoardService boardService;
	
	@Bean
	public GameService gameService(@Value("${game.players.max}") int maxPlayers) {
		return new SimpleGameService(maxPlayers, boardService);
	}
}
