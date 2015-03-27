package fr.sii.survival.core.ext.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.sii.survival.core.domain.Game;
import fr.sii.survival.core.exception.ExtensionInitializationException;
import fr.sii.survival.core.ext.EnemyExtension;

/**
 * Provider that execute multiple delegate providers
 * 
 * @author Aurélien Baudet
 *
 */
public class MultiProvider implements ExtensionProvider {

	private List<ExtensionProvider> delegates;

	public MultiProvider(List<ExtensionProvider> delegates) {
		super();
		this.delegates = delegates;
	}

	public MultiProvider(ExtensionProvider... delegates) {
		this(Arrays.asList(delegates));
	}

	@Override
	public List<EnemyExtension> getEnemies(Game game) throws ExtensionInitializationException {
		List<EnemyExtension> extensions = new ArrayList<>();
		for (ExtensionProvider delegate : delegates) {
			extensions.addAll(delegate.getEnemies(game));
		}
		return extensions;
	}

}
