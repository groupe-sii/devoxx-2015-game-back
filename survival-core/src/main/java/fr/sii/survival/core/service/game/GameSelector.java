package fr.sii.survival.core.service.game;

import java.util.List;

import fr.sii.survival.core.domain.Game;

/**
 * Interface for managers that are able to select a game
 * 
 * @author Aurélien Baudet
 *
 */
public interface GameSelector {
	/**
	 * Selects a game in the list. If no game could be selected, then returns
	 * null
	 * 
	 * @param games
	 *            the list of available games
	 * @return the selected game or null if no game could be selected
	 */
	public Game select(List<Game> games);

}
