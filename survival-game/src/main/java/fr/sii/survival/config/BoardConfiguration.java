package fr.sii.survival.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.board.RandomCellProvider;
import fr.sii.survival.core.service.board.SimpleBoardService;

@Configuration
public class BoardConfiguration {
	@Bean
	public BoardService boardService() {
		return new SimpleBoardService(10, 10, new RandomCellProvider());
	}
}
