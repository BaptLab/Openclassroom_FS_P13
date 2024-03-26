package com.openclassrooms.Openclassrooms_FS_P13_POC.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.Openclassrooms_FS_P13_POC.models.ChatMessage;
import com.openclassrooms.Openclassrooms_FS_P13_POC.repository.ChatMessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

	private ChatMessageRepository chatMessageRepository;
	private ChatRoomService chatRoomService;

	public ChatMessage save(ChatMessage chatMessage) {
		var chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
				.orElseThrow();
		chatMessage.setChatId(chatId);
		ChatMessage savedMessaged = chatMessageRepository.save(chatMessage);
		return savedMessaged;
	}

	public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
		var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
				return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
	}

}
