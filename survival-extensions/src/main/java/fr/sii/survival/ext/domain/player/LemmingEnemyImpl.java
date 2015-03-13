package fr.sii.survival.ext.domain.player;

import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.core.domain.player.Life;
import fr.sii.survival.core.domain.player.PlayerInfo;
import fr.sii.survival.core.domain.player.SimpleLife;
import fr.sii.survival.core.domain.player.States;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.ext.DelegateEnemyManager;
import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.behavior.action.FleeingEnemyManager;
import fr.sii.survival.core.ext.behavior.move.RandomAroundNearManager;
import fr.sii.survival.core.ext.behavior.target.RandomPlayerTargetManager;


public class LemmingEnemyImpl extends EnemyExtension implements Enemy {

	private Life life;
	
	private PlayerInfo info;
	
	private int id;
	
	private States states;
	
	private DelegateEnemyManager simpleIAEnemyManager;

	private static int counter = 0;
	
	private final static String name = "Lemming";
	
	private final static String avatar = "lemming.png";
	
	/**
	 * Creates a Lemming with 1000 points of life (current and maximum)
	 * 
	 */
	public LemmingEnemyImpl() {
		this(new PlayerInfo(name, avatar), 1000);
	}

	/**
	 * Creates a Lemming with the provided points of life (current and maximum)
	 * 
	 * @param info
	 *            the player information (name and avatar)
	 * @param life
	 *            the points of life of the wizard (current and maximum)
	 */
	public LemmingEnemyImpl(PlayerInfo info, int life) {
		this(info, new SimpleLife(life));
	}

	/**
	 * Creates a Lemming with provided life
	 * 
	 * @param info
	 *            the enemy information (name and avatar)
	 * @param life
	 *            the life of the lemming
	 */
	public LemmingEnemyImpl(PlayerInfo info, Life life) {
		this(info, life, new States());
	}

	/**
	 * Creates a Lemming with provided life and applied states
	 * 
	 * @param info
	 *            the player information (name and avatar)
	 * @param life
	 *            the life of the wizard
	 * @param states
	 *            the states to apply on
	 */
	public LemmingEnemyImpl(PlayerInfo info, Life life, States states) {
//		super(new RandomAroundNearManager(), new AreaActionManager(delegate, shape), new RandomPlayerTargetManager());
		super();
		simpleIAEnemyManager = new DelegateEnemyManager(new RandomAroundNearManager(), new FleeingEnemyManager(actionService), new RandomPlayerTargetManager());
		id = counter++;
		this.info = info;
		this.life = life;
		this.states = states;
	}
	
	@Override
	public Life getLife() {
		return life;
	}

	@Override
	public String getId() {
		return "LemmingEnemyImpl:" + id;
	}

	@Override
	public States getStates() {
		return states;
	}

	@Override
	public PlayerInfo getPlayerInfo() {
		return info;
	}

	@Override
	public void run(GameContext context) throws GameException {
		simpleIAEnemyManager.run(context);
	}

	@Override
	public EnemyExtension getExtension() {
		return simpleIAEnemyManager;
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
		LemmingEnemyImpl other = (LemmingEnemyImpl) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{").append(getId()).append(info).append(life).append(states).append("}");
		return builder.toString();
	}
}
