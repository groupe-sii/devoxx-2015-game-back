package fr.sii.survival.core.ext.registry;

import java.util.ArrayList;
import java.util.List;

import fr.sii.survival.core.ext.EnemyExtension;

public class SimpleEnemyExtensionRegistry implements ExtensionRegistry {

	private List<EnemyExtension> enemyExtensions;
	
	public SimpleEnemyExtensionRegistry() {
		this(new ArrayList<>());
	}

	public SimpleEnemyExtensionRegistry(List<EnemyExtension> enemyExtensions) {
		super();
		this.enemyExtensions = enemyExtensions;
	}

	@Override
	public void register(EnemyExtension extension) {
		enemyExtensions.add(extension);
	}

	@Override
	public List<EnemyExtension> getEnemyExtensions() {
		return enemyExtensions;
	}

}
