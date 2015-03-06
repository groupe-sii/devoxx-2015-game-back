package fr.sii.survival.dto;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.domain.player.Player;

public class PlayerAndGame {
	private Player player;
	
	private Game game;

	public PlayerAndGame(Player player, Game game) {
		super();
		this.player = player;
		this.game = game;
	}

	public Player getPlayer() {
		return player;
	}

	public Game getGame() {
		return game;
	}
}
