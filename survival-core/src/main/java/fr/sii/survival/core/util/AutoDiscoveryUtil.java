package fr.sii.survival.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import fr.sii.survival.core.ext.EnemyExtension;

public class AutoDiscoveryUtil {
	private AutoDiscoveryUtil() {
		super();
	}

	/**
	 * Automatically find implementations of the provided class in the provided
	 * packages.
	 * 
	 * @param clazz
	 *            the base interface or class
	 * @param provider
	 *            the function that checks if the class is allowed or not
	 * @param packageNames
	 *            the list of packages where to find implementations
	 * @return the list of found implementations
	 * @param <T>
	 *            The base type of the class to look for
	 * @param <R>
	 *            The type for the provided object for each found class
	 */
	public static <T, R> List<R> find(Class<T> clazz, Function<Class<? extends T>, R> provider, String... packageNames) {
		Configuration builder = getBuilder(packageNames);
		Reflections reflections = new Reflections(builder);
		Set<Class<? extends T>> subTypes = reflections.getSubTypesOf(clazz);
		List<R> impls = new ArrayList<>(subTypes.size());
		for (Class<? extends T> type : subTypes) {
			R result = provider.apply(type);
			if (result != null) {
				impls.add(result);
			}
		}
		return impls;
	}

	public static Configuration getBuilder(String... packageNames) {
		ConfigurationBuilder builder = ClassLoaderHelper.getReflectionsBuilder();
		for (String packageName : packageNames) {
			builder.addUrls(ClasspathHelper.forPackage(packageName));
		}
		builder.addUrls(ClasspathHelper.forPackage(EnemyExtension.class.getPackage().getName()));
		return builder;
	}

}
