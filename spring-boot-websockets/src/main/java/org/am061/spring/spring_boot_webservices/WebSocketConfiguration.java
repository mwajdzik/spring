package org.am061.spring.spring_boot_webservices;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    // http://localhost:8080/socket/

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOrigins("*")
                // we will not use 'clean' websockets, but with SockJS
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // An app prefix, when our client will send message through socket, the URL to send will look approximately like this: http://localhost:8080/app/.../...
        // We will have just one subscription — /chat - clients will subscribe to this subscription and will wait from messages from the server.
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/chat");
    }
}