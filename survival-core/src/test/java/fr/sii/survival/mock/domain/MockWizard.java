package fr.sii.survival.mock.domain;

import fr.sii.survival.core.domain.player.Life;
import fr.sii.survival.core.domain.player.SimpleLife;
import fr.sii.survival.core.domain.player.Wizard;

public class MockWizard implements Wizard {

	@Override
	public Life getLife() {
		return new SimpleLife(1000);
	}

	@Override
	public void setLife(Life life) {
		// nothing to do
	}

}
