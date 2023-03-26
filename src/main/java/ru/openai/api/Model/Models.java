package ru.openai.api.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum Models {
    @JsonProperty("model")
    CHAT("gpt-3.5-turbo"),
    @JsonProperty("model")
    DAVINCHI("text-davinci-003");

    @Getter
    @JsonIgnore
    private final String modelName;
    Models(String name){
        this.modelName=name;
    }


}
