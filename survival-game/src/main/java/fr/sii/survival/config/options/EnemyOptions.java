package fr.sii.survival.config.options;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="game.enemy")
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
	
	/**
	 * Patterns used for excluding some enemy implementations
	 */
	private List<Pattern> excludes = new ArrayList<>(); 

	public int getMaxEnemies() {
		return maxEnemies;
	}

	public int getSpecialEvery() {
		return specialEvery;
	}

	public int getAddDelay() {
		return addDelay;
	}

	public List<Pattern> getExcludes() {
		return excludes;
	}

	public void setMaxEnemies(int maxEnemies) {
		this.maxEnemies = maxEnemies;
	}

	public void setSpecialEvery(int specialEvery) {
		this.specialEvery = specialEvery;
	}

	public void setAddDelay(int addDelay) {
		this.addDelay = addDelay;
	}

	public void setExcludes(List<Pattern> excludes) {
		this.excludes = excludes;
	}
}
