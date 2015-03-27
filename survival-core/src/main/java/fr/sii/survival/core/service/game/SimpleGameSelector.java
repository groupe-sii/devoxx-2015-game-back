package fr.sii.survival.core.service.game;

import java.util.List;

import fr.sii.survival.core.domain.Game;

/**
 * Simple selector that adds players into the first game that is not full.
 * 
 * @author Aurélien Baudet
 *
 */
public class SimpleGameSelector implements GameSelector {

	@Override
	public Game select(List<Game> games) {
		for(Game game : games) {
			if(!game.isFull()) {
				return game;
			}
		}
		return null;
	}

}
