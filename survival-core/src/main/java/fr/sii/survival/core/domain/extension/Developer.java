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
}
