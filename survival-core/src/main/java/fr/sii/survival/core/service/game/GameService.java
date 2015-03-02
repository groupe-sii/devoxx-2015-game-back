package fr.sii.survival.core.service.game;

import fr.sii.survival.core.domain.board.Board;
import fr.sii.survival.core.domain.player.Player;

public interface GameService {

	/**
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public Board newPlayer(Player player) throws Exception;
	
	/**
	 * 
	 * @param message
	 * @throws Exception
	 */
	public void removePlayer(Player player) throws Exception;
	
	
}
