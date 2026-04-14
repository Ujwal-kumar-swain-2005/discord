package com.substring.chat.repositories;

import com.substring.chat.entities.NexchatConversation;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NexchatRepository extends JpaRepository<NexchatConversation, String> {

    Optional<NexchatConversation> findByUsername(String username);
}