package fr.sii.survival.core.reload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.survival.core.util.ClassLoaderHelper;

/**
 * A manager that listen to reload events and that refresh the class loader
 * 
 * @author Aurélien Baudet
 *
 */
public class ExtensionsClassLoaderReloader {
	private static final Logger LOG = LoggerFactory.getLogger(ExtensionsClassLoaderReloader.class);

	public ExtensionsClassLoaderReloader(ReloadWatcher watcher) {
		super();
		watcher.addReloadListener(() -> reload());
	}

	public void reload() {
		LOG.info("Change detected, reloading class loader");
		ClassLoaderHelper.refresh();
	}
}
