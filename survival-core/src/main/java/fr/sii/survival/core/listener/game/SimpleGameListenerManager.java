package fr.sii.survival.core.listener.game;

import java.util.HashMap;
import java.util.Map;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.GameListenerException;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;

// TODO: manage locks to avoid concurrentmodifexception
public class SimpleGameListenerManager implements GameListenerManager {

	private Map<String, GameListener> listeners;
	
	private MessageService errorService;
	
	private ExtensionService extensionService;
	
	public SimpleGameListenerManager(MessageService errorService, ExtensionService extensionService) {
		this(new HashMap<>(), errorService, extensionService);
	}
	
	public SimpleGameListenerManager(Map<String, GameListener> listeners, MessageService errorService, ExtensionService extensionService) {
		super();
		this.listeners = listeners;
		this.errorService = errorService;
		this.extensionService = extensionService;
	}

	@Override
	public void addGameListener(GameListener listener) {
		String key = getKey(listener);
		if(!listeners.containsKey(key)) {
			listeners.put(key, listener);
		} else {
			throw new IllegalArgumentException("Board listener can't be registered, same listener is already registered. key="+key);
		}
	}

	@Override
	public void removeGameListener(GameListener listener) {
		String key = getKey(listener);
		if(listeners.containsKey(key)) {
			listeners.remove(key);
		}
	}


	@Override
	public void triggerStarted(Game game) {
		for(GameListener listener : listeners.values()) {
			try {
				listener.started(game);
			} catch(Throwable e) {
				errorService.addError(new GameListenerException("failed to trigger moved event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerStopped(Game game) {
		for(GameListener listener : listeners.values()) {
			try {
				listener.stopped(game);
			} catch(Throwable e) {
				errorService.addError(new GameListenerException("failed to trigger moved event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerJoined(Player player, Game game) {
		for(GameListener listener : listeners.values()) {
			try {
				listener.joined(player, game);
			} catch(Throwable e) {
				errorService.addError(new GameListenerException("failed to trigger moved event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}

	@Override
	public void triggerLeaved(Player player, Game game) {
		for(GameListener listener : listeners.values()) {
			try {
				listener.leaved(player, game);
			} catch(Throwable e) {
				errorService.addError(new GameListenerException("failed to trigger moved event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
			}
		}
	}
	
	private String getKey(GameListener listener) {
		return listener.getClass().toString();
	}
}
