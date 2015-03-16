package fr.sii.survival.config.options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerOptions {
	/**
	 * Life options
	 */
	private LifeOptions lifeOptions;

	@Autowired
	public PlayerOptions(LifeOptions lifeOptions) {
		super();
		this.lifeOptions = lifeOptions;
	}

	public LifeOptions getLifeOptions() {
		return lifeOptions;
	}
}
