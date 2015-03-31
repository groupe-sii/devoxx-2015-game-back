package fr.sii.survival.core.listener.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.GameListenerException;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;

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
		for(GameListener listener : getListeners()) {
			try {
				listener.started(game);
			} catch(Exception e) {
				manageError("started", listener, e);
			}
		}
	}

	@Override
	public void triggerStopped(Game game) {
		for(GameListener listener : getListeners()) {
			try {
				listener.stopped(game);
			} catch(Exception e) {
				manageError("stopped", listener, e);
			}
		}
	}

	@Override
	public void triggerJoined(Player player, Game game) {
		for(GameListener listener : getListeners()) {
			try {
				listener.joined(player, game);
			} catch(Exception e) {
				manageError("joined", listener, e);
			}
		}
	}

	@Override
	public void triggerLeaved(Player player, Game game) {
		for(GameListener listener : getListeners()) {
			try {
				listener.left(player, game);
			} catch(Exception e) {
				manageError("leaved", listener, e);
			}
		}
	}

	private List<GameListener> getListeners() {
		return new CopyOnWriteArrayList<>(listeners.values());
	}
	
	private String getKey(GameListener listener) {
		return listener.getClass().toString();
	}

	private void manageError(String event, GameListener listener, Exception e) {
		errorService.addError(new GameListenerException("failed to trigger '"+event+"' event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
	}
}
