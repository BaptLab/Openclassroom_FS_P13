package com.openclassrooms.Openclassrooms_FS_P13_POC.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.openclassrooms.Openclassrooms_FS_P13_POC.models.ChatMessage;
import com.openclassrooms.Openclassrooms_FS_P13_POC.models.ChatNotification;
import com.openclassrooms.Openclassrooms_FS_P13_POC.services.ChatMessageService;

import lombok.RequiredArgsConstructor;

@Controller
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from http://localhost:420
@RequiredArgsConstructor
public class ChatController {

	private final ChatMessageService chatMessageService;
	private final SimpMessagingTemplate messaginTemplate;

	@MessageMapping("/chat")
	public void processMessage(
			@Payload ChatMessage chatMessage
			) {
		ChatMessage savedMsg = chatMessageService.save(chatMessage);
		messaginTemplate.convertAndSendToUser(
				chatMessage.getRecipientId(), "/queue/messages",
				ChatNotification.builder()
				.id(savedMsg.getId())
				.senderId(savedMsg.getSenderId()).recipientId(savedMsg.getRecipientId())
				.content(savedMsg.getContentChatMsg())
				.build());
				
	}

	@GetMapping("/messages/{senderId}/{recipientId}")
	public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable("senderId") String senderId,
			@PathVariable("recipientId") String recipientId) {
		return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
	}

}
