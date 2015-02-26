package fr.sii.survival.controller;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.error.ErrorListener;

@Controller
public class ErrorController implements ErrorListener {

	@SendTo("error")
	public Error notifyError(GameException e) {
		return new Error(e);
	}

	@Override
	public void error(GameException e) {
		notifyError(e);
	}
}
