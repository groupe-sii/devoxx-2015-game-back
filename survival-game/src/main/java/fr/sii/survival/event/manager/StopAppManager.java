package fr.sii.survival.event.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.helper.MultiGameHelper;
import fr.sii.survival.core.service.game.GameService;

@Component
public class StopAppManager implements ApplicationListener<ContextClosedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(StopAppManager.class);

	@Autowired
	GameService gameService;
	
	@Autowired
	MultiGameHelper gameHelper;
	
	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		for(Game game : gameHelper.getGames()) {
			try {
				gameService.stop(game);
			} catch (GameException e) {
				logger.error("Failed to stop game {}. Cause: {}", game, e);
			}
		}
	}

}
