package ng.samuel.spring_chat.dto.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import ng.samuel.spring_chat.model.Status;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
public class MessageRequest {




    //@NotEmpty(message = "sender must not be empty")
    private String senderName;

    private String receiverName;

    private String message;

    private String date;

    private Status status;



}
