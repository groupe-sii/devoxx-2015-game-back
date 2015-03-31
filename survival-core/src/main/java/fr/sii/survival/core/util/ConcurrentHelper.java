package fr.sii.survival.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import fr.sii.survival.core.domain.Game;

/**
 * Utility class that provides scheduled services and timers to be used by some
 * instances like action behaviors.
 * 
 * @author Aurélien Baudet
 *
 */
public class ConcurrentHelper {
	private static Map<String, GameConcurrentFactory> factoriesByGameId = new HashMap<>();

	public static GameConcurrentFactory getGameFactory(Game game) {
		return getGameFactory(game.getId());
	}

	public static GameConcurrentFactory getGameFactory(String gameId) {
		if (!factoriesByGameId.containsKey(gameId)) {
			factoriesByGameId.put(gameId, new GameConcurrentFactory(gameId));
		}
		return factoriesByGameId.get(gameId);
	}

	public static void stop(Game game) {
		stop(game.getId());
	}

	public static void stop(String gameId) {
		GameConcurrentFactory factory = factoriesByGameId.get(gameId);
		if(factory!=null) {
			factory.stop();
			factoriesByGameId.remove(gameId);
		}
	}

	public static class GameConcurrentFactory {
		private final Map<String, ScheduledExecutorService> scheduledServices;

		private final Map<String, Timer> timers;

		private final String gameId;

		public GameConcurrentFactory(String gameId) {
			super();
			this.gameId = gameId;
			timers = new HashMap<>();
			scheduledServices = new HashMap<>();
		}

		public ScheduledExecutorService getScheduledService(String name) {
			if (!scheduledServices.containsKey(name)) {
				scheduledServices.put(name, Executors.newSingleThreadScheduledExecutor());
			}
			return scheduledServices.get(name);
		}

		public Timer getTimer(String name) {
			if (!timers.containsKey(name)) {
				timers.put(name, new Timer(gameId + "-" + name));
			}
			return timers.get(name);
		}

		public void stop() {
			for (Timer timer : timers.values()) {
				timer.cancel();
			}
			timers.clear();
			for (ScheduledExecutorService service : scheduledServices.values()) {
				service.shutdown();
			}
			scheduledServices.clear();
		}
	}
}
