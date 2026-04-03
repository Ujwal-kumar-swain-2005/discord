package com.example.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${FRONTEND_URL:http://localhost:5173}")
    private String frontendUrl;


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        config.enableSimpleBroker("/topic");

        config.setApplicationDestinationPrefixes("/app");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        String[] origins = Arrays.stream(frontendUrl.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);

        boolean hasWildcard = Arrays.stream(origins).anyMatch(o -> o.contains("*"));

        if (hasWildcard) {
            registry.addEndpoint("/chat")
                    .setAllowedOriginPatterns(origins)
                    .withSockJS();
        } else {
            registry.addEndpoint("/chat")
                    .setAllowedOrigins(origins)
                    .withSockJS();
        }
    }
}