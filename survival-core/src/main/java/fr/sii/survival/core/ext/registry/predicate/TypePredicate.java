package fr.sii.survival.core.ext.registry.predicate;

import java.util.function.Predicate;

/**
 * Filters extensions that implements/extends the provided type.
 * 
 * @author aurelien
 *
 */
public class TypePredicate implements Predicate<Class<?>> {

	private Class<?> type;
	
	public TypePredicate(Class<?> type) {
		super();
		this.type = type;
	}

	@Override
	public boolean test(Class<?> ext) {
		return type.isAssignableFrom(ext);
	}

}
