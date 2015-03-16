package fr.sii.survival.config.options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GameOptions {
	/**
	 * The scheduling delay in milliseconds
	 */
	private final int schedulingDelay;
	
	/**
	 * The maximum number of allowed players in the game (0 to disable)
	 */
	private final int maxPlayers;
	
	/**
	 * Automatically starts the game when there is at least one player
	 */
	private final boolean autoStart;
	
	/**
	 * Automatically stops the game when there is no more player
	 */
	private final boolean autoStop;

	@Autowired
	public GameOptions(@Value("${game.scheduling.delay}") int schedulingDelay, @Value("${game.players.max}") int maxPlayers, @Value("${game.start.auto}") boolean autoStart, @Value("${game.stop.auto}") boolean autoStop) {
		super();
		this.schedulingDelay = schedulingDelay;
		this.maxPlayers = maxPlayers;
		this.autoStart = autoStart;
		this.autoStop = autoStop;
	}

	public int getSchedulingDelay() {
		return schedulingDelay;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public boolean isAutoStart() {
		return autoStart;
	}

	public boolean isAutoStop() {
		return autoStop;
	}
}
