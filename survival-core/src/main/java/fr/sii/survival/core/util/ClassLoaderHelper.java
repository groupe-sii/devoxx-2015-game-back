package fr.sii.survival.core.util;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 * A helper for managing class loaders. This helper is needed due to hot
 * reloading of extensions. The extensions are in a separate class loader to
 * allow the reloading of the classes.
 * 
 * @author Aurélien Baudet
 *
 */
public class ClassLoaderHelper {
	/**
	 * The current class loader for the extensions
	 */
	private static ClassLoader classLoader;

	/**
	 * The URL where to find the extensions. If null, then no specific class
	 * loader is used
	 */
	private static URL extensionsUrl;

	private ClassLoaderHelper() throws MalformedURLException {
		super();
	}

	/**
	 * Initialize the extensions class loader management. If this method is not
	 * called, then no extensions will be loaded.
	 * 
	 * @param extensionsUrl
	 *            the URL of either the JAR that contains the classes of the
	 *            extensions or a folder that points to the classes of the
	 *            extensions
	 */
	public static void initialize(URL extensionsUrl) {
		ClassLoaderHelper.extensionsUrl = extensionsUrl;
	}

	/**
	 * Get the URL for the extensions (either points to a JAR or a folder)
	 * 
	 * @return the URL to the extensions classes
	 */
	public static URL getExtensionsURL() {
		return extensionsUrl;
	}

	/**
	 * Get the specific class loader for extensions. This method also handles
	 * the lazy initialization of the specific class loader if not already done.
	 * 
	 * @return the specific class loader if extensions URL is provided, null
	 *         otherwise
	 */
	public static synchronized ClassLoader getExtensionsClassLoader() {
		if (classLoader == null && extensionsUrl != null) {
			classLoader = new URLClassLoader(new URL[] { extensionsUrl }, ClasspathHelper.contextClassLoader());
		}
		return classLoader;
	}

	/**
	 * Asks to refresh the extensions class loader. The class loader is not
	 * reloaded here. The class loader will be reloaded when trying to access
	 * it. This is useful for lazy loading of classes.
	 */
	public static void refresh() {
		// reset the class loader reference => it will automatically reloaded
		// when trying to access it
		classLoader = null;
	}

	/**
	 * Helper method for all methods that are using {@link Reflections}. This
	 * method adds information about the specific class loader. If this specific
	 * configuration for {@link Reflections} is not used then the classes or
	 * resources of the extensions will not be found.
	 * 
	 * @return the reflections configuration builder
	 */
	public static ConfigurationBuilder getReflectionsBuilder() {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.addClassLoaders(getClassLoaders());
		builder.addUrls(ClasspathHelper.forClassLoader(ClassLoaderHelper.getExtensionsClassLoader()));
		return builder;
	}

	/**
	 * Get the list of class loaders to use for loading classes and retrieving
	 * resources. It provides the context class loader, a static class loader
	 * and the extensions specific class loader if initialized.
	 * 
	 * @return the list of class loaders
	 */
	public static synchronized List<ClassLoader> getClassLoaders() {
		ClassLoader[] defaultClassLoaders = ClasspathHelper.classLoaders(ClasspathHelper.contextClassLoader(), ClasspathHelper.staticClassLoader());
		List<ClassLoader> classLoaders = new ArrayList<>(Arrays.asList(defaultClassLoaders));
		ClassLoader extensionsClassLoader = ClassLoaderHelper.getExtensionsClassLoader();
		if (extensionsClassLoader != null) {
			classLoaders.add(extensionsClassLoader);
		}
		return classLoaders;
	}

	/**
	 * Helper method to get a resource from its path like {@link ClassLoader}
	 * .getResourceAsStream. The path is the relative path to the root
	 * classpath. The name must not start with '/'. The resource is tried to
	 * load for every class loader available.
	 * 
	 * This is different from {@link Class}.getResourceAsStream because the
	 * {@link Class} method first looks to resource relative to the defined
	 * class (in the package).
	 * 
	 * @param path
	 *            the path of the resource to load as input stream
	 * @return the stream if found, null otherwise
	 */
	public static synchronized InputStream getResourceAsStream(String path) {
		for (ClassLoader classLoader : getClassLoaders()) {
			InputStream stream = classLoader.getResourceAsStream(path);
			if (stream != null) {
				return stream;
			}
		}
		return null;
	}

	/**
	 * Helper method to get URL of a resource from its path like
	 * {@link ClassLoader}.getResource. The path is the relative path to the
	 * root classpath. The name must not start with '/'. The resource is tried
	 * to load for every class loader available.
	 * 
	 * This is different from {@link Class}.getResource because the
	 * {@link Class} method first looks to resource relative to the defined
	 * class (in the package).
	 * 
	 * @param path
	 *            the path of the resource
	 * @return the URL for the resource
	 */
	public static synchronized URL getResource(String path) {
		for (ClassLoader classLoader : getClassLoaders()) {
			URL url = classLoader.getResource(path);
			if (url != null) {
				return url;
			}
		}
		return null;
	}

}
