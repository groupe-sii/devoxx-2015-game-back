package fr.sii.survival.core.domain.player;

public class SimpleWizard implements Wizard {
	/**
	 * Counter used to generate a unique id
	 */
	private static int counter;

	/**
	 * The unique id of the wizard
	 */
	private int id;

	/**
	 * The life information
	 */
	private Life life;

	/**
	 * The current states of the wizard
	 */
	private States states;

	/**
	 * Creates a wizard with 1000 points of life (current and maximum)
	 */
	public SimpleWizard() {
		this(1000);
	}

	/**
	 * Creates a wizard with the provided points of life (current and maximum)
	 * 
	 * @param life
	 *            the points of life of the wizard (current and maximum)
	 */
	public SimpleWizard(int life) {
		this(new SimpleLife(life));
	}

	/**
	 * Creates a wizard with the provided maximum points of life and the actual
	 * wizard points of life
	 * 
	 * @param life
	 *            the actual points of life
	 * @param max
	 *            the maximum points of life
	 */
	public SimpleWizard(int life, int max) {
		this(new SimpleLife(life, max));
	}

	/**
	 * Creates a wizard with provided life
	 * 
	 * @param life
	 *            the life of the wizard
	 */
	public SimpleWizard(Life life) {
		this(life, new States());
	}

	/**
	 * Creates a wizard with provided life and applied states
	 * 
	 * @param life
	 *            the life of the wizard
	 * @param states
	 *            the states to apply on
	 */
	public SimpleWizard(Life life, States states) {
		super();
		id = counter++;
		this.life = life;
		this.states = states;
	}

	@Override
	public Life getLife() {
		return life;
	}

	@Override
	public States getStates() {
		return new States();
	}

	@Override
	public String getId() {
		return "SimpleWizard:" + id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		SimpleWizard other = (SimpleWizard) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{").append(getId()).append(life).append(states).append("}");
		return builder.toString();
	}
}
