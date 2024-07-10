package ng.samuel.spring_chat.dto.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
public class MessageRequest {


    private String message;
    @NotEmpty(message = "sender must not be empty")
    private String sender;

}
