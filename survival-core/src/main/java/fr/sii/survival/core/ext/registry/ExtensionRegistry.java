package fr.sii.survival.core.ext.registry;

import java.util.List;

import fr.sii.survival.core.ext.EnemyExtension;

public interface ExtensionRegistry {
	public void register(EnemyExtension extension);
	
	public List<EnemyExtension> getEnemyExtensions();
}
