package com.substring.chat.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

/**
 * Message is stored as part of a Room via @ElementCollection.
 * @Embeddable means it has no independent identity/table — it is
 * always stored inside Room's collection table.
 * (An @Entity cannot be used with @ElementCollection; that requires @Embeddable.)
 */
@Embeddable
public class Message {
    private String sender;

    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime timeStamp;


    public Message() {
    }

    public Message(String sender, String content, LocalDateTime timeStamp) {
        this.sender = sender;
        this.content = content;
        this.timeStamp = timeStamp;
    }

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

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timeStamp = LocalDateTime.now();
    }
}