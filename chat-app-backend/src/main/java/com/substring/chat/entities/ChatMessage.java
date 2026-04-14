package com.substring.chat.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private NexchatConversation conversation;

    public ChatMessage() {}

    @PrePersist
    public void setTimestamp() {
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public NexchatConversation getConversation() {
        return conversation;
    }

    public void setConversation(NexchatConversation conversation) {
        this.conversation = conversation;
    }

    public ChatMessage(Long id, String role, String content, LocalDateTime timestamp, com.substring.chat.entities.NexchatConversation conversation) {
        this.id = id;
        this.role = role;
        this.content = content;
        this.timestamp = timestamp;
        this.conversation = conversation;
    }
}