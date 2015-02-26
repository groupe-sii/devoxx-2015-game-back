package fr.sii.survival.core.listener.action;


public interface ActionListenerRegistry {
	
	public void addActionListener(ActionListener listener);
	
	public void removeActionListener(ActionListener listener);
}
