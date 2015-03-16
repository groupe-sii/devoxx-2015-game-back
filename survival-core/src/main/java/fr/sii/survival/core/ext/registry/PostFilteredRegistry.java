package fr.sii.survival.core.ext.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import fr.sii.survival.core.ext.EnemyExtension;

/**
 * Registry that filters the extensions according to the provided predicate.
 * All extensions are registered in the delegate registry.
 * The filter is applied when retrieving an extension.
 * 
 * @author aurelien
 *
 */
public class PostFilteredRegistry implements ExtensionRegistry {

	private ExtensionRegistry delegate;
	
	private Predicate<Class<?>> predicate;
	
	public PostFilteredRegistry(Predicate<Class<?>> predicate, ExtensionRegistry delegate) {
		super();
		this.predicate = predicate;
		this.delegate = delegate;
	}

	@Override
	public void register(Class<? extends EnemyExtension> extension) {
		delegate.register(extension);
	}

	@Override
	public List<Class<? extends EnemyExtension>> getEnemyExtensions() {
		List<Class<? extends EnemyExtension>> enemyExtensions = delegate.getEnemyExtensions();
		List<Class<? extends EnemyExtension>> filtered = new ArrayList<>(enemyExtensions.size());
		for(Class<? extends EnemyExtension> extension : enemyExtensions) {
			if(predicate.test(extension)) {
				filtered.add(extension);
			}
		}
		return filtered;
	}

}
