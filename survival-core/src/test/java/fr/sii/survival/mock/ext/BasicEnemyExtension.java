package fr.sii.survival.mock.ext;

import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.annotation.Developer;

@Developer(value="abaudet", name="Aurélien Baudet")
public class BasicEnemyExtension extends EnemyExtension {


	public BasicEnemyExtension() {
		super("basic", null, 1000);
	}

	@Override
	public void run(GameContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

}
