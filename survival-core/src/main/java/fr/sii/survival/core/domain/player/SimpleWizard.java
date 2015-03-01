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
	
	public SimpleWizard() {
		this(new SimpleLife(1000));
	}
	
	public SimpleWizard(Life life) {
		this(life, new States());
	}

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
		return "SimpleWizard:"+id;
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
		builder.append(id).append(life).append(states);
		return builder.toString();
	}
}
