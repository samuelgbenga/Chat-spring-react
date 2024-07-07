package ng.samuel.spring_chat.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {

    private String responseCode;

    private String responseMessage;

    private SenderMessageInfo messageInfo;

}
