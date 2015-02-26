package fr.sii.survival.core.listener.error;

public interface ErrorListenerRegistry {
	public void addErrorListener(ErrorListener listener);
	
	public void removeErrorListener(ErrorListener listener);
}
