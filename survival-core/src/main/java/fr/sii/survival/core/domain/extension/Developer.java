package fr.sii.survival.core.domain.extension;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Developer information useful to inform if an extension has generated an error
 * 
 * @author Aurélien Baudet
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
		return new HashCodeBuilder().append(nickname).hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || !(obj instanceof Developer)) {
			return false;
		}
		Developer other = (Developer) obj;
		return new EqualsBuilder().append(nickname, other.nickname).isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Developer [nickname=").append(nickname).append(", name=").append(name).append(", email=").append(email).append("]");
		return builder.toString();
	}
}
