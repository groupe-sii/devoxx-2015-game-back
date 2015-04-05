package fr.sii.survival.core.reload;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Base class that helps registering, unregistering and triggering listeners.
 * 
 * @author Aurélien Baudet
 *
 */
public abstract class BaseReloadWatcher implements ReloadWatcher {

	/**
	 * The list of listeners
	 */
	private final List<ReloadListener> listeners;

	public BaseReloadWatcher() {
		this(new ArrayList<>());
	}

	public BaseReloadWatcher(List<ReloadListener> listeners) {
		super();
		this.listeners = listeners;
	}

	@Override
	public void addReloadListener(ReloadListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeReloadListener(ReloadListener listener) {
		listeners.remove(listener);
	}

	protected void triggerListeners() {
		for (ReloadListener listener : new CopyOnWriteArrayList<>(listeners)) {
			listener.reloaded();
		}
	}
}
