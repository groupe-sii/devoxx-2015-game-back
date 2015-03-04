package fr.sii.survival.controller;

import static fr.sii.survival.config.MessageConfiguration.MESSAGE_PUBLISH_PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.message.Error;
import fr.sii.survival.core.domain.message.Message;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.message.MessageListener;

@Controller
public class MessageController extends ErrorController implements MessageListener {
	@Autowired
	SimpMessagingTemplate template;

	@Override
	public void error(GameException e) {
		template.convertAndSend(MESSAGE_PUBLISH_PREFIX+"/error", new Error(e));
	}

	@Override
	public void message(Message message) {
		template.convertAndSend(MESSAGE_PUBLISH_PREFIX, message);
	}
}
