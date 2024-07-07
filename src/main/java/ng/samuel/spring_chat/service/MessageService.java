package ng.samuel.spring_chat.service;

import ng.samuel.spring_chat.dto.request.MessageRequest;
import ng.samuel.spring_chat.dto.response.MessageResponse;
import ng.samuel.spring_chat.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MessageService {

    MessageResponse saveMessage(MessageRequest messageRequest);

    Page<Message> getMessageByPage(int page, int size);

   MessageResponse getRecent20Messages();

     MessageResponse getRecentNthMessages(int limit);



}
