package fr.sii.survival.core.listener.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.AddImage;
import fr.sii.survival.core.domain.action.MoveImage;
import fr.sii.survival.core.domain.action.RemoveImage;
import fr.sii.survival.core.domain.action.StartAnimation;
import fr.sii.survival.core.domain.action.StopAnimation;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.action.UpdatePosition;
import fr.sii.survival.core.domain.action.UpdateStates;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.exception.ActionListenerException;
import fr.sii.survival.core.service.extension.ExtensionService;
import fr.sii.survival.core.service.message.MessageService;

// TODO: manage locks to avoid concurrentmodifexception
public class SimpleActionListenerManager implements ActionListenerManager {

	private Map<String, ActionListener> listeners;
	
	private MessageService errorService;
	
	private ExtensionService extensionService;
	
	public SimpleActionListenerManager(MessageService errorService, ExtensionService extensionService) {
		this(new HashMap<>(), errorService, extensionService);
	}
	
	public SimpleActionListenerManager(Map<String, ActionListener> listeners, MessageService errorService, ExtensionService extensionService) {
		super();
		this.listeners = listeners;
		this.errorService = errorService;
		this.extensionService = extensionService;
	}

	@Override
	public void addActionListener(ActionListener listener) {
		String key = getKey(listener);
		if(!listeners.containsKey(key)) {
			listeners.put(key, listener);
		} else {
			throw new IllegalArgumentException("Action listener can't be registered, same listener is already registered. key="+key);
		}
	}

	@Override
	public void removeActionListener(ActionListener listener) {
		String key = getKey(listener);
		if(listeners.containsKey(key)) {
			listeners.remove(key);
		}
	}

	@Override
	public void triggerLifeUpdated(Game game, Player player, UpdateLife action) {
		for(ActionListener listener : getListeners()) {
			try {
				listener.lifeUpdated(game, player, action);
			} catch(Exception e) {
				manageError("life updated", listener, e);
			}
		}
	}

	@Override
	public void triggerPositionChanged(Game game, Player player, UpdatePosition action) {
		for(ActionListener listener : getListeners()) {
			try {
				listener.positionChanged(game, player, action);
			} catch(Exception e) {
				manageError("position changed", listener, e);
			}
		}
	}

	@Override
	public void triggerImageAdded(Game game, AddImage action) {
		for(ActionListener listener : getListeners()) {
			try {
				listener.imageAdded(game, action);
			} catch(Exception e) {
				manageError("image added", listener, e);
			}
		}
	}
	
	@Override
	public void triggerImageMoved(Game game, MoveImage action) {
		for(ActionListener listener : getListeners()) {
			try {
				listener.imageMoved(game, action);
			} catch(Exception e) {
				manageError("image moved", listener, e);
			}
		}
	}

	@Override
	public void triggerImageRemoved(Game game, RemoveImage action) {
		for(ActionListener listener : getListeners()) {
			try {
				listener.imageRemoved(game, action);
			} catch(Exception e) {
				manageError("image removed", listener, e);
			}
		}
	}
	
	@Override
	public void triggerStateChanged(Game game, Player player, UpdateStates action) {
		for(ActionListener listener : getListeners()) {
			try {
				listener.stateChanged(game, player, action);
			} catch(Exception e) {
				manageError("state changed", listener, e);
			}
		}
	}

	@Override
	public void triggerAnimationStarted(Game game, StartAnimation action) {
		for(ActionListener listener : getListeners()) {
			try {
				listener.animationStarted(game, action);
			} catch(Exception e) {
				manageError("animation started", listener, e);
			}
		}
	}

	@Override
	public void triggerAnimationStopped(Game game, StopAnimation action) {
		for(ActionListener listener : getListeners()) {
			try {
				listener.animationStopped(game, action);
			} catch(Exception e) {
				manageError("animation stopped", listener, e);
			}
		}
	}
	
	private List<ActionListener> getListeners() {
		return new CopyOnWriteArrayList<>(listeners.values());
	}

	private void manageError(String event, ActionListener listener, Exception e) {
		errorService.addError(new ActionListenerException("failed to trigger "+event+" event on listener "+listener.getClass().getName(), extensionService.getDeveloper(listener), e));
	}
	
	private String getKey(ActionListener listener) {
		return listener.getClass().toString();
	}
}
