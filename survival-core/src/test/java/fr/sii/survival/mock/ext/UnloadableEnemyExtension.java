package fr.sii.survival.mock.ext;

import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.ext.annotation.Developer;

@Developer(value="abaudet", name="Aurélien Baudet")
public class UnloadableEnemyExtension implements EnemyExtension {
	public UnloadableEnemyExtension(String param) {
		
	}

	@Override
	public void run() {
		
	}

	@Override
	public void init() {
		
	}
}
