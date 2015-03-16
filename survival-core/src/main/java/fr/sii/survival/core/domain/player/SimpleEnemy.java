package fr.sii.survival.core.domain.player;

public class SimpleEnemy extends SimplePlayer implements Enemy {

	public SimpleEnemy(PlayerInfo info, int life, int max) {
		super(info, life, max);
	}

	public SimpleEnemy(PlayerInfo info, int life) {
		super(info, life);
	}

	public SimpleEnemy(PlayerInfo info, Life life, States states) {
		super(info, life, states);
	}

	public SimpleEnemy(PlayerInfo info, Life life) {
		super(info, life);
	}

	public SimpleEnemy(PlayerInfo info) {
		super(info);
	}

	public SimpleEnemy(String name, String avatar, int life, int max) {
		super(name, avatar, life, max);
	}

	public SimpleEnemy(String name, String avatar, int life) {
		super(name, avatar, life);
	}

	public SimpleEnemy(String name, String avatar, Life life, States states) {
		super(name, avatar, life, states);
	}

	public SimpleEnemy(String name, String avatar) {
		super(name, avatar);
	}
}
