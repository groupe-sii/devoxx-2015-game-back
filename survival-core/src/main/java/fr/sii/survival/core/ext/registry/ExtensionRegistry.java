package fr.sii.survival.core.ext.registry;

import java.util.List;

import fr.sii.survival.core.ext.EnemyExtension;

public interface ExtensionRegistry {
	/**
	 * Register an extension
	 * 
	 * @param extension
	 *            the extension to register
	 */
	public void register(Class<EnemyExtension> extension);

	/**
	 * Give the whole list of available extensions
	 * 
	 * @return the list of available extensions
	 */
	public List<Class<EnemyExtension>> getEnemyExtensions();
}
