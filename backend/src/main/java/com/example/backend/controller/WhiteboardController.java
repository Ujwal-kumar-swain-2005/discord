package com.example.backend.controller;

import com.example.backend.entities.WhiteboardEvent;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller

public class WhiteboardController {

    public WhiteboardController() {
    }

    @MessageMapping("/whiteboard/{roomId}")
    @SendTo("/topic/whiteboard/{roomId}")
    public WhiteboardEvent handleWhiteboardEvent(
            @DestinationVariable String roomId,
            @Payload WhiteboardEvent event
    ) {

        event.setRoomId(roomId);

        return event;
    }
}
