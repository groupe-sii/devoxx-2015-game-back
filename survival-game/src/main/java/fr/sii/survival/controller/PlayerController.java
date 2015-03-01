package fr.sii.survival.controller;

import static fr.sii.survival.config.PlayerConfiguration.PLAYER_PUBLISH_PREFIX;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.action.ChangeStates;
import fr.sii.survival.core.domain.action.StateChange;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.player.PlayerListener;
import fr.sii.survival.dto.PlayerLifeUpdate;

@Controller
public class PlayerController implements PlayerListener {
	@Autowired
	SimpMessagingTemplate template;

	@Override
	public void dead(Player player) {
		template.convertAndSend(PLAYER_PUBLISH_PREFIX+"/died", player);
	}

	@Override
	public void revived(Player player) {
		template.convertAndSend(PLAYER_PUBLISH_PREFIX+"/revived", player);
	}

	@Override
	public void hit(Player player, int damage) {
		template.convertAndSend(PLAYER_PUBLISH_PREFIX+"/hit", new PlayerLifeUpdate(player, damage));
	}

	@Override
	public void healed(Player player, int amount) {
		template.convertAndSend(PLAYER_PUBLISH_PREFIX+"/healed", new PlayerLifeUpdate(player, amount));
	}

	@Override
	public void statesChanged(Player player, List<StateChange> changes) {
		template.convertAndSend(PLAYER_PUBLISH_PREFIX+"/states", new ChangeStates(player, changes));
	}

}
