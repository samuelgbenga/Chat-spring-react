package ng.samuel.spring_chat.dto.response;


import lombok.Builder;
import lombok.Data;
import ng.samuel.spring_chat.model.Message;

import java.util.List;

@Data
@Builder
public class SenderMessageInfo {

    private String message;

    private String sender;

    private List<Message> messageList;
}
