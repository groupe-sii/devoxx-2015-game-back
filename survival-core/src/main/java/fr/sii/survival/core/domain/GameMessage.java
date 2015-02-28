package fr.sii.survival.core.domain;

public class GameMessage {

	
	
	public GameMessage() {
		super();
	}
	public GameMessage(String action, String data) {
		super();
		this.action = action;
		this.data = data;
	}
	private String action;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	private String data;
}
