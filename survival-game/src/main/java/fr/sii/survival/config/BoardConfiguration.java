package fr.sii.survival.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import fr.sii.survival.controller.GameBoardController;
import fr.sii.survival.core.domain.player.Life;
import fr.sii.survival.core.domain.player.SimpleLife;
import fr.sii.survival.core.domain.player.Wizard;
import fr.sii.survival.core.listener.board.BoardListenerManager;
import fr.sii.survival.core.listener.board.SimpleBoardListenerManager;
import fr.sii.survival.core.service.board.BoardService;
import fr.sii.survival.core.service.board.RandomCellProvider;
import fr.sii.survival.core.service.board.SimpleBoardService;
import fr.sii.survival.core.service.error.ErrorService;
import fr.sii.survival.core.service.extension.ExtensionService;

@Configuration
@EnableScheduling
public class BoardConfiguration {
	@Autowired
	ErrorService errorService;
	
	@Autowired
	ExtensionService extensionService;

	@Autowired
	GameBoardController boardController;
	
	@Value("${game.board.width}")
	int width;
	
	@Value("${game.board.height}")
	int height;
	
	@PostConstruct
	public void init() {
		boardService().addBoardListener(boardController);
	}
	
	@Scheduled(fixedDelay=1000)
	public void test() {
		boardService().add(new Wizard() {
			
			@Override
			public void setLife(Life life) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Life getLife() {
				return new SimpleLife(1000);
			}
		});
	}
	
	@Bean
	public BoardService boardService() {
		return new SimpleBoardService(width, height, cellProvider(), boardListenerManager());
	}

	@Bean
	public RandomCellProvider cellProvider() {
		return new RandomCellProvider();
	}

	@Bean
	public BoardListenerManager boardListenerManager() {
		return new SimpleBoardListenerManager(errorService, extensionService);
	}
}
