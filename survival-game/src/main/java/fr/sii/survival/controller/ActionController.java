package fr.sii.survival.controller;

import static fr.sii.survival.config.ActionConfiguration.ACTION_PUBLISH_PREFIX;
import static fr.sii.survival.config.ActionConfiguration.CLIENT_SEND_PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.exception.ActionException;
import fr.sii.survival.core.listener.action.ActionListenerAdapter;
import fr.sii.survival.core.service.action.ActionService;

@Controller
public class ActionController extends ActionListenerAdapter {
	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	ActionService actionService;

	@MessageMapping(CLIENT_SEND_PREFIX)
	public void execute(Action action) throws ActionException {
		actionService.execute(action);
	}

	@Override
	public void imageMoved(MoveImage action) {
		template.convertAndSend(ACTION_PUBLISH_PREFIX+"/image/moved", action);
	}
	
}
