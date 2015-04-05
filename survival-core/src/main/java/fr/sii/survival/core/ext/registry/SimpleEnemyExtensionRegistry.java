package fr.sii.survival.core.ext.registry;

import java.util.ArrayList;
import java.util.List;

import fr.sii.survival.core.ext.EnemyExtension;

/**
 * Simple registry that stores in memory enemy extensions.
 * 
 * @author Aurélien Baudet
 *
 */
public class SimpleEnemyExtensionRegistry implements EnemyRegistry {

	private List<Class<EnemyExtension>> enemyExtensions;

	public SimpleEnemyExtensionRegistry() {
		this(new ArrayList<>());
	}

	public SimpleEnemyExtensionRegistry(List<Class<EnemyExtension>> enemyExtensions) {
		super();
		this.enemyExtensions = enemyExtensions;
	}

	@Override
	public void reset() {
		enemyExtensions.clear();
	}

	@Override
	public void register(Class<EnemyExtension> extension) {
		enemyExtensions.add(extension);
	}

	@Override
	public List<Class<EnemyExtension>> getEnemyExtensions() {
		return enemyExtensions;
	}

}
