package fr.sii.survival.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.message.Message;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.message.MessageListener;

public class LogMessageListener implements MessageListener {
	private static final Logger logger = LoggerFactory.getLogger(LogMessageListener.class);

	@Override
	public void error(GameException e) {
		logger.error(e.getMessage(), e);
	}

	@Override
	public void message(Message message) {
		switch (message.getLevel()) {
			case DEBUG:
				logger.debug(message.getMessage());
			break;
			case INFO:
				logger.info(message.getMessage());
			break;
			case WARNING:
				logger.warn(message.getMessage());
			break;
		}
	}

}
