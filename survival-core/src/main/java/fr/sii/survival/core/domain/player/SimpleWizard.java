package fr.sii.survival.core.domain.player;

import fr.sii.survival.core.domain.image.Image;

public class SimpleWizard extends SimplePlayer implements Wizard {

	public SimpleWizard(PlayerInfo info, int life, int max) {
		super(info, life, max);
	}

	public SimpleWizard(PlayerInfo info, int life) {
		super(info, life);
	}

	public SimpleWizard(PlayerInfo info, Life life, States states) {
		super(info, life, states);
	}

	public SimpleWizard(PlayerInfo info, Life life) {
		super(info, life);
	}

	public SimpleWizard(PlayerInfo info) {
		super(info);
	}

	public SimpleWizard(String name, Image avatar, int life, int max) {
		super(name, avatar, life, max);
	}

	public SimpleWizard(String name, Image avatar, int life) {
		super(name, avatar, life);
	}

	public SimpleWizard(String name, Image avatar, Life life, States states) {
		super(name, avatar, life, states);
	}

	public SimpleWizard(String name, Image avatar) {
		super(name, avatar);
	}
}
