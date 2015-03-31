package fr.sii.survival.core.service.action.rule;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.action.Action;
import fr.sii.survival.core.domain.action.UpdateLife;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.ext.annotation.Developer;
import fr.sii.survival.core.util.CellUtil;

/**
 * Rule that prevent actions to be executed if player targets a cell that is too
 * far from him. This rule takes effect only for {@link UpdateLife} action
 * 
 * @author Aurélien Baudet
 */
@Developer(value="abaudet", name="Aurélien Baudet", email="abaudet@sii.fr")
public class LimitUpdateLifeRangeRule implements AllowActionRule {

	private int max;
	
	public LimitUpdateLifeRangeRule() {
		this(5);
	}

	public LimitUpdateLifeRangeRule(int max) {
		super();
		this.max = max;
	}

	@Override
	public boolean isAllowed(Game game, Player player, Action action) {
		if(action instanceof UpdateLife) {
			return isAllowed(game, player, (UpdateLife) action);
		} else {
			return true;
		}
	}

	protected boolean isAllowed(Game game, Player player, UpdateLife action) {
		return CellUtil.distance(game.getBoard().getCell(player), action.getCell())<max;
	}
}
