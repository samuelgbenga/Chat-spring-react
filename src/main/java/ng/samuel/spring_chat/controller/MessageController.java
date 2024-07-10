package ng.samuel.spring_chat.controller;


import lombok.RequiredArgsConstructor;
import ng.samuel.spring_chat.dto.request.MessageRequest;
import ng.samuel.spring_chat.model.Message;
import ng.samuel.spring_chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.WebSocketStompClient;


@Controller
@RequiredArgsConstructor
public class MessageController {



    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private final MessageService messageService;


    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public MessageRequest sendMessage(@Payload MessageRequest chatMessage) {
        //messageService.saveMessage(chatMessage);
        return chatMessage;

    }

    @MessageMapping("/private-message")
    public Message addUser(@Payload Message chatMessage) {
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getReceiverName(),"/private", chatMessage);
        return chatMessage;
    }


    // create the connection

}
