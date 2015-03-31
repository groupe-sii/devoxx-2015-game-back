package fr.sii.survival.event.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.service.game.GameService;
import fr.sii.survival.core.util.MultiGameHelper;

@Component
public class StopAppManager implements ApplicationListener<ContextClosedEvent> {
	private static final Logger LOG = LoggerFactory.getLogger(StopAppManager.class);

	@Autowired
	GameService gameService;
	
	@Autowired
	MultiGameHelper gameHelper;
	
	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		// stop all games
		for(Game game : gameHelper.getGames()) {
			try {
				if(gameService.isStarted(game)) {
					gameService.stop(game);
				}
			} catch (GameException e) {
				LOG.error("Failed to stop game {}. Cause: {}", game, e);
			}
		}
	}

}
