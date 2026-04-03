package com.example.backend.repositories;

import com.example.backend.entities.NexchatConversation;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NexchatRepository extends JpaRepository<NexchatConversation,Long> {

}
