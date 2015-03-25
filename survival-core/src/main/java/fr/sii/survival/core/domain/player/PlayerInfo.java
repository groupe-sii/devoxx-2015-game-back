package fr.sii.survival.core.domain.player;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.sii.survival.core.domain.image.Image;

public class PlayerInfo {
	/**
	 * The name of the player
	 */
	private String name;
	
	/**
	 * The image of the player
	 */
	private Image avatar;
	
	public PlayerInfo(String name) {
		this(name, null);
	}

	public PlayerInfo(String name, Image avatar) {
		super();
		this.name = name;
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getAvatar() {
		return avatar;
	}

	public void setAvatar(Image avatar) {
		this.avatar = avatar;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avatar).append(name).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof PlayerInfo)) {
			return false;
		}
		PlayerInfo other = (PlayerInfo) obj;
		return new EqualsBuilder().append(avatar, other.avatar).append(name, other.name).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{name=").append(name).append(", avatar=").append(avatar).append("}");
		return builder.toString();
	}
}
