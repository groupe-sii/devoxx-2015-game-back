package fr.sii.survival.controller;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;

import fr.sii.survival.core.domain.message.Error;

// TODO: This controller won't work standalone... It must be used by inheritance due to error handler search limited to current class only
public class ErrorController {
	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	public Error handleException(Throwable exception) {
		return new Error(exception);
	}
}
