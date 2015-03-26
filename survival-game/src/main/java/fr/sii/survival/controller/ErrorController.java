package fr.sii.survival.controller;

import static fr.sii.survival.config.WebSocketConfig.SERVER_PUBLISH_PREFIX;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;

import fr.sii.survival.core.domain.message.Error;

// TODO: This controller won't work standalone... It must be used by inheritance due to error handler search limited to current class only
public class ErrorController {
	@MessageExceptionHandler
	@SendToUser(SERVER_PUBLISH_PREFIX+"/errors")
	public Error handleException(Exception exception) {
		return new Error(exception);
	}
}
