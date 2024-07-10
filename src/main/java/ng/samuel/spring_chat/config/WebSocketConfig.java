package ng.samuel.spring_chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    /*
This line of code configures a WebSocket endpoint /chat in a Spring Boot application, allowing WebSocket
 connections with SockJS fallback support, and permits connections from any origin.
 */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {


        //  this is how you create the socket js connection
        // var socket = new SockJS('http://localhost:8080/chat');
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }


    /*
enableSimpleBroker: Specifies the endpoints where clients can receive messages from the server.
setApplicationDestinationPrefixes: Specifies the prefix for endpoints where clients send messages to the server.
 */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        //enableSimpleBroker: Manages how messages are delivered to clients.
        // this is the web broker endpoint that manages how message are delivered
        // so sendTo annotation or its equivalent will always begin with this prefix
            registry.enableSimpleBroker("/chatroom", "/user");


            //setApplicationDestinationPrefixes: Manages how clients send messages to the server.
            // this is used from the front end
            // it used by the client to send message to the service
            // it is a prefix used by the client to send message to the server
            registry.setApplicationDestinationPrefixes("/app");


            // for private user
            registry.setUserDestinationPrefix("/user");

    }
}



