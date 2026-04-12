package com.substring.chat.repositories;

import com.substring.chat.entities.NexchatConversation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NexchatRepository extends MongoRepository<NexchatConversation, String> {

    Optional<NexchatConversation> findByUsername(String username);
}