package fr.sii.survival.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.service.action.ActionService;

@Controller
@MessageMapping("action")
public class ActionController {
	@Autowired
	ActionService actionService;

	@MessageMapping("execute")
	public void execute(Action action) {
		actionService.execute(action);
	}
	
}
