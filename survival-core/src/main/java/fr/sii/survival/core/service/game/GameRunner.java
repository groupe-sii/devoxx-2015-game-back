package fr.sii.survival.core.service.game;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.exception.GameException;

public interface GameRunner extends Runnable {
	public void stop() throws GameException;

	public Game getGame();

	public void setGame(Game game);

	public void setGameService(GameService gameService);
}
