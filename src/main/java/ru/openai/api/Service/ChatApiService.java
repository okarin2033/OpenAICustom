package ru.openai.api.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.openai.api.DTO.Suggestion;
import ru.openai.api.Model.ChatCompletion;
import ru.openai.api.Model.Message;
import ru.openai.api.Model.Models;
import ru.openai.api.Model.RequestBody;
import ru.openai.api.Service.Config.Configuration;

import java.util.Arrays;
import java.util.List;

@Service
public class ChatApiService implements ChatApi{
    private final RestTemplate restTemplate = new RestTemplate();
    private final String token= Configuration.getAPI_KEY();
    private final String BASE_URL = "https://api.openai.com/v1/chat/completions";
    @Override
    public Message getAnswer(List<Message> messages, String system) {
        messages.add(0, new Message("system",system));

        //headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer "+token);

        // create a RequestBody object
        RequestBody requestBody = new RequestBody();
        requestBody.setModel(Models.CHAT.getModelName());
        requestBody.setMessages(messages);


        HttpEntity<RequestBody> requestEntity = new HttpEntity<>(requestBody, headers);
        ChatCompletion response= restTemplate.postForObject(BASE_URL, requestEntity, ChatCompletion.class);


        assert response != null;
        ChatCompletion.Choice.Message answer= response.getChoices().get(0).getMessage();
        return new Message(answer.getRole(), answer.getContent());
    }

    @Override
    public Suggestion getSuggestions(List<Message> messages) {
        messages.add(0, new Message("system","Твоя задача предсказывать следующие возможные вопросы ученика. " +
                "Выведи 3 возможных вопроса и больше ничего не пиши. " +
                "После каждого вопроса начинай новую строку.Пример:\n" +
                "1. Вопрос 1?\n" +
                "2. Вопрос 2?\n" +
                "3. Вопрос 3?"));

        messages.add(new Message("user","Придумай 3 вопроса, которые может дальше задать ученик. Напиши их и больше ничего не выводи."));
        //headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer "+token);

        // create a RequestBody object
        RequestBody requestBody = new RequestBody();
        requestBody.setModel(Models.CHAT.getModelName());
        requestBody.setMessages(messages);


        HttpEntity<RequestBody> requestEntity = new HttpEntity<>(requestBody, headers);
        ChatCompletion response= restTemplate.postForObject(BASE_URL, requestEntity, ChatCompletion.class);

        ChatCompletion.Choice.Message answer= response.getChoices().get(0).getMessage();

        Suggestion suggestion = new Suggestion();
        String[] questionsArray = answer.getContent().split("\n");
        suggestion.setResult(List.of(questionsArray));
        suggestion.setPreviousQuestion(messages.get(messages.size()-2).getContent());
        return suggestion;
    }
}
