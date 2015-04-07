package fr.sii.survival.core.reload;

/**
 * Listener definition to reload events
 * 
 * @author Aurélien Baudet
 *
 */
@FunctionalInterface
public interface ReloadListener {
	/**
	 * Fired when a reload event has been triggered
	 */
	public void reloaded();
}
