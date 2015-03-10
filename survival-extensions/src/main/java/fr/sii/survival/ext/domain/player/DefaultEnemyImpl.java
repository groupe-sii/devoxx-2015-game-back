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


public class DefaultEnemyImpl extends EnemyExtension implements Enemy {

	private Life life;
	
	private PlayerInfo info;
	
	private String id;
	
	private States states;
	
	private DelegateEnemyManager simpleIAEnemyManager;

	private static int counter = 0;
	
	private final static String name = "Lemming";
	
	private final static String avatar = "";
	
	/**
	 * Creates a Lemming with 1000 points of life (current and maximum)
	 * 
	 */
	public DefaultEnemyImpl() {
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
	public DefaultEnemyImpl(PlayerInfo info, int life) {
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
	public DefaultEnemyImpl(PlayerInfo info, Life life) {
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
	public DefaultEnemyImpl(PlayerInfo info, Life life, States states) {
//		super(new RandomAroundNearManager(), new AreaActionManager(delegate, shape), new RandomPlayerTargetManager());
		super();
		simpleIAEnemyManager = new DelegateEnemyManager(new RandomAroundNearManager(), new FleeingEnemyManager(actionService), new RandomPlayerTargetManager());
		id = ""+counter++;
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
		return id;
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

}
