package ru.openai.api.DTO;

import lombok.Data;

import java.util.List;

@Data
public class Suggestion {
    List<String> result;
    String previousQuestion;
}
