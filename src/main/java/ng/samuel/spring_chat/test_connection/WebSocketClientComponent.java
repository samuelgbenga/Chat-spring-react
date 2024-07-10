package ng.samuel.spring_chat.test_connection;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class WebSocketClientComponent {


    private  WebSocketStompClient stompClient;


    private StompSessionHandler sessionHandler;

    public void connect() throws ExecutionException, InterruptedException {
        String url = "http://localhost:8080/chat";
        StompSession stompSession = stompClient.connect(url, sessionHandler).get();
        stompSession.send("/app/greetings", "Hello new user");

        stompSession.subscribe("/topic/greetings", new StompSessionHandlerAdapter() {
            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                String msg = (String) payload;
                System.out.println("Received : " + msg);
            }
        });
    }

    @Bean
    public WebSocketStompClient stompClient() {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        return stompClient;
    }

    @Bean
    public StompSessionHandler sessionHandler() {
        return new CustomStompSessionHandler();
    }

    private class CustomStompSessionHandler extends StompSessionHandlerAdapter {
        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            System.out.println("Connected to the WebSocket server");
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            System.out.println("Received: " + payload);
        }
    }
}
