package com.substring.chat.playload;

import lombok.Data;


@Data
public class NextchatRequest {
    String username;
    String question;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
