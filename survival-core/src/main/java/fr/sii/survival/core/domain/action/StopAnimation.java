package fr.sii.survival.core.domain.action;


/**
 * Action that stops an animation
 * 
 * @author aurelien
 *
 */
public class StopAnimation implements Action {
	/**
	 * The name of the animation to stop
	 */
	private String name;

	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	@Deprecated
	public StopAnimation() {
		super();
	}

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
