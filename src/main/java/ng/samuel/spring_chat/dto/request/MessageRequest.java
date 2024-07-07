package ng.samuel.spring_chat.dto.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageRequest {

    private String message;

    private String sender;

}
