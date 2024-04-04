package com.openclassrooms.Openclassrooms_FS_P13_POC.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.openclassrooms.Openclassrooms_FS_P13_POC.models.Message;

import java.util.HashSet;
import java.util.Set;

@Controller
public class WebSocketController {
    
    private final Set<String> connectedUsers = new HashSet<>();
    private final SimpMessagingTemplate messagingTemplate;
    
    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chatroom")
    @SendTo("/topic/chatroom")
    public String handleChatMessage(String message) throws Exception {
        System.out.println("Message received in handleChatMessage: " + message);
        return message;
    }

    @MessageMapping("/user/connect")
    public void connect(String userId) {
        System.out.println("Received the user: " + userId);
        connectedUsers.add(userId);
        broadcastConnectedUsers();
    }

    @MessageMapping("/user/disconnect")
    public void disconnect(String userId) {
        System.out.println("User disconnected: " + userId);
        connectedUsers.remove(userId);
        broadcastConnectedUsers();
    }

    @Scheduled(fixedDelay = 10000) // Broadcast connected users every 10 seconds (adjust as needed)
    public void broadcastConnectedUsers() {
        System.out.println("Broadcasting connected users");
        messagingTemplate.convertAndSend("/topic/connectedUsers", connectedUsers);
    }
}
