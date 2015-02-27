package fr.sii.survival.dto;

import fr.sii.survival.core.domain.player.Player;

public class PlayerLifeUpdate {
	/**
	 * The player who life changed
	 */
	private Player player;

	/**
	 * The life amount (positive means player was healed, negative means the
	 * player was hit)
	 */
	private int amount;

	public PlayerLifeUpdate(Player player, int amount) {
		super();
		this.player = player;
		this.amount = amount;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
