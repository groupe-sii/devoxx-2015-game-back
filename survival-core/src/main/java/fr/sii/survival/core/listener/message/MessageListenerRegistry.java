package fr.sii.survival.core.listener.message;

public interface MessageListenerRegistry {
	public void addMessageListener(MessageListener listener);
	
	public void removeMessageListener(MessageListener listener);
}
