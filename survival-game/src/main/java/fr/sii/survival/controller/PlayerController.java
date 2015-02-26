package fr.sii.survival.controller;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.listener.player.PlayerListener;
import fr.sii.survival.dto.PlayerLifeUpdate;

@Controller
public class PlayerController implements PlayerListener {
	
	@SendTo("player/died")
	public Player notifyDead(Player player) {
		return player;
	}

	@SendTo("player/revived")
	public Player notifyRevived(Player player) {
		return player;
	}

	@SendTo("player/hit")
	public PlayerLifeUpdate notifyHit(Player player, int damage) {
		return new PlayerLifeUpdate(player, damage);
	}

	@SendTo("player/healed")
	public PlayerLifeUpdate notifyHealed(Player player, int amount) {
		return new PlayerLifeUpdate(player, amount);
	}
	
	@Override
	public void dead(Player player) {
		notifyDead(player);
	}

	@Override
	public void revived(Player player) {
		notifyRevived(player);
	}

	@Override
	public void hit(Player player, int damage) {
		notifyHit(player, damage);
	}

	@Override
	public void healed(Player player, int amount) {
		notifyHealed(player, amount);
	}

}
