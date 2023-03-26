package ru.openai.api.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.openai.api.Model.Message;

import java.util.List;

@Data
public class ChatRequest {
    @JsonProperty("messages")
    List<Message> messages;
    @JsonProperty("system")
    String system;
}
