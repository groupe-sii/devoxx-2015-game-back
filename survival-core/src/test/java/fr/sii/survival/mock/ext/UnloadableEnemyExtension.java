package fr.sii.survival.mock.ext;

import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.annotation.Developer;

@Developer(value="cpasmoi", name="")
public class UnloadableEnemyExtension extends EnemyExtension {
	public UnloadableEnemyExtension(String param) {
		super(null, null, 0);
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
