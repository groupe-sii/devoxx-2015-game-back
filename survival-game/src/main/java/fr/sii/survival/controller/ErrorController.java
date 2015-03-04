package fr.sii.survival.controller;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import fr.sii.survival.core.domain.message.Error;

import fr.sii.survival.core.exception.GameException;

@Controller
public class ErrorController {
	@MessageExceptionHandler(GameException.class)
	@SendToUser(value = "/queue/errors", broadcast = false)
	public Error handleGameException(GameException exception) {
		return new Error(exception);
	}
	
	@MessageExceptionHandler(Throwable.class)
	@SendToUser(value = "/queue/errors", broadcast = false)
	public Error handleException(Throwable exception) {
		return new Error(exception);
	}
}
