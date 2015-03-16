package fr.sii.survival.config.options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GameOptions {
	/**
	 * The scheduling delay in milliseconds
	 */
	private int schedulingDelay;
	
	/**
	 * The maximum number of allowed players in the game (0 to disable)
	 */
	private int maxPlayers;

	@Autowired
	public GameOptions(@Value("${game.scheduling.delay}") int schedulingDelay, @Value("${game.players.max}") int maxPlayers) {
		super();
		this.schedulingDelay = schedulingDelay;
		this.maxPlayers = maxPlayers;
	}

	public int getSchedulingDelay() {
		return schedulingDelay;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}
}
