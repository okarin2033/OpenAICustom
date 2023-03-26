package ru.openai.api.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import ru.openai.api.Model.Message;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestBody {
    @JsonProperty("model")
    String model;
    @JsonProperty("messages")
    List<Message> messages;

}
