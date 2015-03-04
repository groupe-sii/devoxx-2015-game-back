package fr.sii.survival.core.domain.player;

public class PlayerInfo {
	private static final String DEFAULT_AVATAR = "default";

	/**
	 * The name of the player
	 */
	private String name;
	
	/**
	 * The image of the player
	 */
	private String avatar;
	
	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	public PlayerInfo() {
		super();
	}

	public PlayerInfo(String name) {
		this(name, DEFAULT_AVATAR);
	}

	public PlayerInfo(String name, String avatar) {
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerInfo other = (PlayerInfo) obj;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{name=").append(name).append(", avatar=").append(avatar).append("}");
		return builder.toString();
	}
}
