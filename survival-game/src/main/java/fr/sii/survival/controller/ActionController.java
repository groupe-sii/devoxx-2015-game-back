package fr.sii.survival.controller;

import static fr.sii.survival.config.ActionConfiguration.ACTION_PUBLISH_PREFIX;
import static fr.sii.survival.config.ActionConfiguration.CLIENT_SEND_PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.ChangePosition;
import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.action.ActionListener;
import fr.sii.survival.core.service.action.ActionService;

@Controller
public class ActionController implements ActionListener {
	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	ActionService actionService;

	@MessageMapping(CLIENT_SEND_PREFIX)
	public void execute(Action action) {
		actionService.execute(action);
	}

	@Override
	public void lifeUpdated(Player player, UpdateLife action) {
		// nothing to do, PlayerController will already provide life changes to the client
	}

	@Override
	public void positionChanged(Player player, ChangePosition action) {
		// nothing to do, BoardController will already provide life changes to the client
	}

	@Override
	public void imageMoved(MoveImage action) {
		template.convertAndSend(ACTION_PUBLISH_PREFIX+"/image/moved", action);
	}

	@Override
	public void stateChanged(Player player, ChangeStates action) {
		// nothing to do, PlayerController will already provide life changes to the client
	}
	
}
