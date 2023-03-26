package ru.openai.api.Controller.v1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.openai.api.DTO.ChatRequest;
import ru.openai.api.DTO.Suggestion;
import ru.openai.api.Model.Message;
import ru.openai.api.Service.ChatApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ChatController {

    final ChatApiService apiService;
    @Autowired
    public ChatController(ChatApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/chat")
    public Message getAnswer(@RequestBody ChatRequest request){
        Message message = apiService.getAnswer(request.getMessages(), request.getSystem());
        return message;
    }

    @PostMapping("/predict")
    public Suggestion test(@RequestBody ChatRequest request){
        Suggestion suggestion = apiService.getSuggestions(request.getMessages());
        return suggestion;
    }
}
