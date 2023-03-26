package ru.openai.api.Service;


import org.springframework.stereotype.Service;
import ru.openai.api.DTO.Suggestion;
import ru.openai.api.Model.Message;

import java.util.ArrayList;
import java.util.List;

public interface ChatApi {

 public Message getAnswer(List<Message> messages, String system);
 public Suggestion getSuggestions(List<Message> messages);
}
