package fr.sii.survival.config.options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.collect.Range;

@Component
public class LifeOptions {
	/**
	 * The range (minimum and maximum value) of the maximum life
	 */
	private final Range<Integer> max;
	
	/**
	 * The default life for players
	 */
	private final int defaultLife;

	public LifeOptions(int defaultLife, Range<Integer> max) {
		super();
		this.max = max;
		this.defaultLife = defaultLife;
	}
	
	@Autowired
	public LifeOptions(@Value("${game.life.default-life}") int defaultLife, @Value("${game.life.max-lower}") int maxLower, @Value("${game.life.max-upper}") int maxUpper) {
		this(defaultLife, Range.closed(maxLower, maxUpper<=0 ? Integer.MAX_VALUE : maxUpper));
	}

	public Range<Integer> getMax() {
		return max;
	}

	public int getDefaultLife() {
		return defaultLife;
	}
}