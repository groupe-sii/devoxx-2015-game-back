package fr.sii.survival.core.ext.registry;

import java.util.List;
import java.util.function.Predicate;

import fr.sii.survival.core.ext.EnemyExtension;

/**
 * Registry that filters the extensions according to the provided predicate.
 * The filter is applied when registering an extension. The delegate registry
 * will only contain accepted extensions. Retrieving the list of extensions
 * directly gives delegate list.
 * 
 * @author aurelien
 *
 */
public class PreFilteredRegistry implements ExtensionRegistry {

	private ExtensionRegistry delegate;
	
	private Predicate<Class<?>> predicate;
	
	public PreFilteredRegistry(Predicate<Class<?>> predicate, ExtensionRegistry delegate) {
		super();
		this.predicate = predicate;
		this.delegate = delegate;
	}

	@Override
	public void register(Class<? extends EnemyExtension> extension) {
		if(predicate.test(extension)) {
			delegate.register(extension);
		}
	}

	@Override
	public List<Class<? extends EnemyExtension>> getEnemyExtensions() {
		return delegate.getEnemyExtensions();
	}

}
