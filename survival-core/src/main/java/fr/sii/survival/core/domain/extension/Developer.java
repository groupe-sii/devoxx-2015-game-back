package fr.sii.survival.core.domain.extension;

/**
 * Developer information useful to inform if an extension has generated an error
 * 
 * @author aurelien
 *
 */
public class Developer {
	private String nickname;

	private String name;

	private String email;

	public Developer(fr.sii.survival.core.ext.annotation.Developer annotation) {
		this(annotation.value(), annotation.name(), annotation.email());
	}
	
	public Developer(String nickname) {
		this(nickname, "");
	}

	public Developer(String nickname, String name) {
		this(nickname, name, "");
	}

	public Developer(String nickname, String name, String email) {
		super();
		this.nickname = nickname;
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
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
		Developer other = (Developer) obj;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Developer [nickname=").append(nickname).append(", name=").append(name).append(", email=").append(email).append("]");
		return builder.toString();
	}
}
