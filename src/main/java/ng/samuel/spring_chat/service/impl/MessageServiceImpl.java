package ng.samuel.spring_chat.service.impl;

import jakarta.security.auth.message.MessageInfo;
import lombok.RequiredArgsConstructor;
import ng.samuel.spring_chat.dto.request.MessageRequest;
import ng.samuel.spring_chat.dto.response.MessageResponse;
import ng.samuel.spring_chat.dto.response.SenderMessageInfo;
import ng.samuel.spring_chat.model.Message;
import ng.samuel.spring_chat.repository.MessageRepository;
import ng.samuel.spring_chat.service.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public MessageResponse saveMessage(MessageRequest messageRequest) {


        Message message = Message.builder()
                .message(messageRequest.getMessage())
                .sender(messageRequest.getSender())
                .build();
        messageRepository.save(message);



        return MessageResponse.builder()
                .responseCode("001")
                .responseMessage("Message saved/sent")
                .messageInfo(SenderMessageInfo.builder()
                        .message(messageRequest.getMessage())
                        .build())
                .build();
    }

    // get by pages
    @Override
    public Page<Message> getMessageByPage(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return  messageRepository.findTopByOrderByIdDesc(pageable);
    }

    // get recent 20's
    @Override
    public MessageResponse getRecent20Messages() {

        List<Message> messageList = messageRepository.findFirst20ByOrderByIdDesc();

        Collections.reverse(messageList);

        return MessageResponse.builder()
                .responseCode("003")
                .responseMessage("Get First 20 messages")
                .messageInfo(SenderMessageInfo.builder()
                        .messageList(messageList)
                        .build())
                .build();


    }

    @Override
    public MessageResponse getRecentNthMessages(int limit) {

        List<Message> messages = messageRepository.findLastNRecords(limit);

        Collections.reverse(messages);

        return MessageResponse.builder()
                .responseCode("004")
                .responseMessage("Resent Nth message return.")
                .messageInfo(SenderMessageInfo.builder()
                        .messageList(messages)
                        .build())
                .build();
    }
}
