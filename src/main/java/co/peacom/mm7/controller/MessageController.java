package co.peacom.mm7.controller;

import co.peacom.mm7.MM7Error;
import co.peacom.mm7.model.MessageRequest;
import co.peacom.mm7.model.MessageResponse;
import co.peacom.mm7.services.MessageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message")
    MessageResponse sendMessage(@Validated @RequestBody MessageRequest messageRequest) throws MM7Error, IOException {
        return messageService.sendMessage(messageRequest);
    }
}
