package fr.sii.survival.core.ext.provider;

/**
 * Provider that call the same delegate provider twice to provide a list of
 * enemies
 * 
 * @author aurelien
 *
 */
public class DoubleProvider extends MultiProvider {

	public DoubleProvider(ExtensionProvider delegate) {
		super(delegate, delegate);
	}

}
