package fr.sii.survival.core.reload;

/**
 * A watcher that checks if a reload is needed. One the watcher is started, it
 * will either:
 * <ul>
 * <li>actively check if reload is needed</li>
 * <li>or will listen to events for checking if reload is needed</li>
 * </ul>
 * 
 * Once a reload is needed, all the registered listeners will be executed to
 * inform the application and to manage the real reload.
 * 
 * @author Aurélien Baudet
 *
 */
public interface ReloadWatcher {
	/**
	 * Starts the watcher either actively or by listening to events
	 */
	public void start();

	/**
	 * Stops the watcher either by stopping the active checking or by stopping
	 * listening to events
	 */
	public void stop();

	/**
	 * Registers a listener that will be triggered every time a reload is needed
	 * 
	 * @param listener
	 *            the listener to register
	 */
	public void addReloadListener(ReloadListener listener);

	/**
	 * Unregisters a previously registered listener
	 * 
	 * @param listener
	 *            the listener to unregister
	 */
	public void removeReloadListener(ReloadListener listener);
}
