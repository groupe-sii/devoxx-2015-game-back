package fr.sii.survival.config.options;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="game")
public class GameOptions {
	/**
	 * The scheduling delay in milliseconds
	 */
	private int schedulingDelay;
	
	/**
	 * The maximum number of allowed players in the game (0 to disable)
	 */
	private int maxPlayers;
	
	/**
	 * Automatically starts the game when there is at least one player
	 */
	private boolean autoStart;
	
	/**
	 * Automatically stops the game when there is no more player
	 */
	private boolean autoStop;

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

	public void setSchedulingDelay(int schedulingDelay) {
		this.schedulingDelay = schedulingDelay;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public void setAutoStart(boolean autoStart) {
		this.autoStart = autoStart;
	}

	public void setAutoStop(boolean autoStop) {
		this.autoStop = autoStop;
	}
	
}
