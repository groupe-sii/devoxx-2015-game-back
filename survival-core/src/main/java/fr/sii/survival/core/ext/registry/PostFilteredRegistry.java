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
 * @author Aurélien Baudet
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
	public void register(Class<EnemyExtension> extension) {
		delegate.register(extension);
	}

	@Override
	public List<Class<EnemyExtension>> getEnemyExtensions() {
		List<Class<EnemyExtension>> enemyExtensions = delegate.getEnemyExtensions();
		List<Class<EnemyExtension>> filtered = new ArrayList<>(enemyExtensions.size());
		for(Class<EnemyExtension> extension : enemyExtensions) {
			if(predicate.test(extension)) {
				filtered.add(extension);
			}
		}
		return filtered;
	}

}
