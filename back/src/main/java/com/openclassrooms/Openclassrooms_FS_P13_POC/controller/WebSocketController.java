package com.openclassrooms.Openclassrooms_FS_P13_POC.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.Openclassrooms_FS_P13_POC.models.Message;
import com.openclassrooms.Openclassrooms_FS_P13_POC.models.User;
import com.openclassrooms.Openclassrooms_FS_P13_POC.services.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class WebSocketController {
    
    private final Set<String> connectedUsers = new HashSet<>();
    private final SimpMessagingTemplate messagingTemplate;
    private final UserService userService;
    
    public WebSocketController(SimpMessagingTemplate messagingTemplate, UserService userService) {
        this.messagingTemplate = messagingTemplate;
		this.userService = userService;
    }

    @MessageMapping("/chatroom")
    @SendTo("/topic/chatroom")
    public Message handleChatMessage(String message) throws Exception {
        System.out.println("Message received in handleChatMessage: " + message);
        ObjectMapper mapper = new ObjectMapper();
        Message chatMessage = mapper.readValue(message, Message.class);
        User user = this.userService.findById(Long.parseLong(chatMessage.getUser()));
        chatMessage.setUser(user.getFirstName());
        return chatMessage; 
    }
    
    

    @MessageMapping("/user/connect")
    public void connect(String userId) {
        System.out.println("Received the user: " + userId);
        User user = this.userService.findById(Long.parseLong(userId));
        connectedUsers.add(user.getFirstName());
        broadcastConnectedUsers();
    }

    @MessageMapping("/user/disconnect")
    public void disconnect(String userId) {
        System.out.println("User disconnected: " + userId);
        User user = this.userService.findById(Long.parseLong(userId));
        connectedUsers.remove(user.getFirstName());
        broadcastConnectedUsers();
    }

    @Scheduled(fixedDelay = 5000) // Broadcast connected users every 10 seconds (adjust as needed)
    public void broadcastConnectedUsers() {
        System.out.println("Broadcasting connected users");
        messagingTemplate.convertAndSend("/topic/connectedUsers", connectedUsers);
    }
}
