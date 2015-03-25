package fr.sii.survival.event.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import fr.sii.survival.controller.GameController;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.session.UserContext;

@Component
public class DisconnectManager implements ApplicationListener<SessionDisconnectEvent> {
	private static final Logger LOG = LoggerFactory.getLogger(DisconnectManager.class);

	@Autowired
	GameController gameController;
	
	@Autowired
	UserContext userContext;
	
	@Override
	public void onApplicationEvent(SessionDisconnectEvent event) {
		try {
			gameController.quit(userContext.getGameId());
		} catch (GameException e) {
			LOG.warn("Failed to disconnect user with session id: {}. Cause: {}", event.getSessionId(), e.getMessage());
		}
	}

}
