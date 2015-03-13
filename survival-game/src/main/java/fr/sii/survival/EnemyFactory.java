package fr.sii.survival;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import com.google.common.collect.Lists;

import fr.sii.survival.core.domain.player.Enemy;
import fr.sii.survival.ext.domain.player.LemmingEnemyImpl;

public class EnemyFactory {

	// Récupération d'un ServiceLoader pour l'interface Authenticator
    private static ServiceLoader<Enemy> serviceLoader = ServiceLoader.load(Enemy.class);
    // Iterator des implémentations trouvées par le ServiceLoader
    private static Iterator<Enemy> iterator = serviceLoader.iterator();
	
    private static List<Enemy> enemies = (List<Enemy>) Lists.newArrayList(iterator);
    private static int size = enemies.size();
    
	public static Enemy getNewEnemy(){
		iterator = serviceLoader.iterator();
		enemies = (List<Enemy>) Lists.newArrayList(iterator);
		size = enemies.size();
		Enemy enemy;
		if (size > 0){
			int index = (int)(Math.ceil(Math.random()*size));
			if (index == 0){
				index = 1;
			}
			enemy = enemies.get(index);
		}else {//ServiceLoader did not find any impl
			enemy = new LemmingEnemyImpl();
		}
		return enemy;
	}
}
