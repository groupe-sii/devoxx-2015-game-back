package fr.sii.survival;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	public static final String TOPIC_PREFIX = "/topic/game";
	public static final String CLIENT_SUBSCRIBE_PREFIX = TOPIC_PREFIX;
	public static final String SERVER_PUBLISH_PREFIX = TOPIC_PREFIX;
	public static final String CLIENT_SEND_PREFIX = TOPIC_PREFIX;
	public static final String ENDPOINT_PREFIX = "/game";
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker(TOPIC_PREFIX);
		config.setApplicationDestinationPrefixes(CLIENT_SEND_PREFIX);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(ENDPOINT_PREFIX).withSockJS()
			/*.setClientLibraryUrl("http://localhost:8000/sockjs.min.js")*/;
	}

}