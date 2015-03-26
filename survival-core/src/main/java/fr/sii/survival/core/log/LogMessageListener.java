package fr.sii.survival.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.domain.message.GameMessage;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.message.MessageListener;

public class LogMessageListener implements MessageListener {
	private static final Logger LOG = LoggerFactory.getLogger(LogMessageListener.class);

	@Override
	public void error(GameException e) {
		LOG.error(e.getMessage(), e);
	}

	@Override
	public void message(GameMessage message) {
		switch (message.getLevel()) {
			case DEBUG:
				LOG.debug(message.getMessage());
			break;
			case WARNING:
				LOG.warn(message.getMessage());
			break;
			case INFO:
			default:
				LOG.info(message.getMessage());
			break;
		}
	}

}
