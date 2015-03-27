package fr.sii.survival.core.domain.action;


/**
 * Action that stops an animation
 * 
 * @author Aurélien Baudet
 *
 */
public class StopAnimation implements Action {
	/**
	 * The name of the animation to stop
	 */
	private String name;

	public StopAnimation(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
