package ru.openai.api.Model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

@Data
public class ChatCompletion {
    private String id;
    private String object;
    private int created;
    private List<Choice> choices;
    private Usage usage;

    @Data
    public static class Choice {
        private int index;
        private Message message;
        private String finish_reason;

        // getters and setters omitted for brevity
        @Data
        public static class Message{
            private String role;
            private String content;

            // getters and setters omitted for brevity
        }
    }
    @Data
    public static class Usage {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;

        // getters and setters omitted for brevity
    }
}
