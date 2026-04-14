package com.substring.chat.services;

import com.substring.chat.entities.ChatMessage;
import com.substring.chat.entities.NexchatConversation;
import com.substring.chat.repositories.NexchatRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
@Service
public class NexchatService {

    private final ChatClient chatClient;
    private final NexchatRepository nexchatRepository;

    public NexchatService(ChatClient chatClient, NexchatRepository nexchatRepository) {
        this.chatClient = chatClient;
        this.nexchatRepository = nexchatRepository;
    }

    public String chat(String username, String question) {

        NexchatConversation conversation = nexchatRepository
                .findByUsername(username)
                .orElseGet(() -> {
                    NexchatConversation newConv = new NexchatConversation();
                    newConv.setUsername(username);
                    return newConv;
                });

        String history = conversation.getMessages().stream()
                .map(m -> m.getRole().toUpperCase() + ": " + m.getContent())
                .collect(Collectors.joining("\n"));

        String fullPrompt = history.isBlank()
                ? question
                : history + "\nUSER: " + question;

        String aiResponse = chatClient.prompt()
                .user(fullPrompt)
                .call()
                .content();

        ChatMessage userMsg = new ChatMessage();
        userMsg.setRole("user");
        userMsg.setContent(question);
        userMsg.setTimestamp(LocalDateTime.now());
        conversation.getMessages().add(userMsg);


        ChatMessage assistantMsg = new ChatMessage();
        assistantMsg.setRole("assistant");
        assistantMsg.setContent(aiResponse);
        assistantMsg.setTimestamp(LocalDateTime.now());
        conversation.getMessages().add(assistantMsg);

        nexchatRepository.save(conversation);

        return aiResponse;
    }


    public void clearHistory(String username) {
        nexchatRepository.findByUsername(username)
                .ifPresent(conv -> {
                    conv.getMessages().clear();
                    nexchatRepository.save(conv);
                });
    }
}