package com.example.backend.entities;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Message {

    private String sender;
    private String content;
    private LocalDateTime timeStamp;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}