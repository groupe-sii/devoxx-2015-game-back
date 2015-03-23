package fr.sii.survival.core.ext.behavior.action;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.RemoveImage;
import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.image.Image;
import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.service.action.ActionService;

public class RemoveImageBehavior extends SimpleActionBehavior {

	private Image image;
	
	public RemoveImageBehavior(ActionService actionService, Enemy enemy, Image image) {
		super(actionService, enemy);
		this.image = image;
	}

	@Override
	public void execute(Game game, Cell cell) throws GameException {
		actionService.execute(game, enemy, new RemoveImage(image, cell));
	}
}