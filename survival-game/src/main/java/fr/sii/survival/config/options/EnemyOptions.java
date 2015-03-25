package fr.sii.survival.config.options;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnemyOptions {
	/**
	 * The maximum number of enemies on the field at the same time
	 */
	private final int maxEnemies;
	
	/**
	 * Number of enemies to wait before invoking a special enemy
	 */
	private final int specialEvery;
	
	/**
	 * The delay to wait before adding a new enemy
	 */
	private final int addDelay;
	
	/**
	 * Patterns used for excluding some enemy implementations
	 */
	private final List<Pattern> excludes; 

	@Autowired
	public EnemyOptions(@Value("${game.enemy.max}") int maxEnemies, @Value("${game.enemy.special.every}") int specialEvery, @Value("${game.enemy.add.delay}") int addDelay, @Value("${game.enemy.exclude}") String[] excludes) {
		super();
		this.maxEnemies = maxEnemies;
		this.specialEvery = specialEvery;
		this.addDelay = addDelay;
		this.excludes = Arrays.asList(excludes).stream().map(e -> Pattern.compile(e)).collect(Collectors.toList());
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

	public List<Pattern> getExcludes() {
		return excludes;
	}
}
