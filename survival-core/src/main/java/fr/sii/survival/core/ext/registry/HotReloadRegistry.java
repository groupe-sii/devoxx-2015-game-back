package fr.sii.survival.core.ext.registry;

import java.util.List;

import fr.sii.survival.core.ext.EnemyExtension;

public class HotReloadRegistry implements ExtensionRegistry {

	private ExtensionRegistry delegate;
	
	private ReloadWatcher watcher;
	
	@Override
	public void register(Class<EnemyExtension> extension) {
		delegate.register(extension);
	}

	@Override
	public List<Class<EnemyExtension>> getEnemyExtensions() {
		return delegate.getEnemyExtensions();
	}
	
}
