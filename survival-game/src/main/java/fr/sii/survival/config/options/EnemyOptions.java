package fr.sii.survival.config.options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnemyOptions {
	/**
	 * The maximum number of enemies on the field at the same time
	 */
	private int maxEnemies;
	
	/**
	 * Number of enemies to wait before invoking a special enemy
	 */
	private int specialEvery;
	
	/**
	 * The delay to wait before adding a new enemy
	 */
	private int addDelay;

	@Autowired
	public EnemyOptions(@Value("${game.enemy.max}") int maxEnemies, @Value("${game.enemy.special.every}") int specialEvery, @Value("${game.enemy.add.delay}") int addDelay) {
		super();
		this.maxEnemies = maxEnemies;
		this.specialEvery = specialEvery;
		this.addDelay = addDelay;
	}

	public int getMaxEnemies() {
		return maxEnemies;
	}

	public int getSpecialEvery() {
		return specialEvery;
	}

	public int getAddDelay() {
		return addDelay;
	}
}
