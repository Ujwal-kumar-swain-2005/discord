package com.example.backend.repositories;

import com.example.backend.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    Room findByRoomId(String roomId);
}