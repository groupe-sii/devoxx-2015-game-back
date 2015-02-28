package fr.sii.survival.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.WebSocketConfig;
import fr.sii.survival.controller.MessageController;
import fr.sii.survival.core.domain.message.Message;
import fr.sii.survival.core.exception.GameException;
import fr.sii.survival.core.listener.message.MessageListener;
import fr.sii.survival.core.service.message.MessageService;

@Configuration
public class MessageConfiguration {
	public static final String MESSAGE_PUBLISH_PREFIX = WebSocketConfig.SERVER_PUBLISH_PREFIX+"/message";

	@Autowired
	MessageController messageController;
	
	@PostConstruct
	public void init() {
		messageService().addMessageListener(messageController);
	}
	
	@Bean
	public MessageService messageService() {
		// TODO: use real error service
		return new MessageService() {
			
			@Override
			public void addError(GameException e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void addMessageListener(MessageListener listener) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeMessageListener(MessageListener listener) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void addMessage(Message message) {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
