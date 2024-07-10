package ng.samuel.spring_chat.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ng.samuel.spring_chat.dto.request.MessageRequest;
import ng.samuel.spring_chat.dto.response.MessageResponse;
import ng.samuel.spring_chat.model.Message;
import ng.samuel.spring_chat.service.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class WebMessageController {

    private final MessageService messageService;

    @PostMapping("/")
    ResponseEntity<MessageResponse> postMessage(@Valid @RequestBody MessageRequest messageRequest){


       MessageResponse messageResponse = messageService.saveMessage(messageRequest);

        return ResponseEntity.ok(messageResponse);
    }

    // getMessageByPag

    @GetMapping("/messages-page")
    public Page<Message> getMessageByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return messageService.getMessageByPage(page, size);
    }

    // getRecent20Messages()

//    @GetMapping("/message20")
//    public MessageResponse getFirst20Messages(){
//
//    }


}
