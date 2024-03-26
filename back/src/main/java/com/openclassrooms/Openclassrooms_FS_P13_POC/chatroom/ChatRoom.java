package com.openclassrooms.Openclassrooms_FS_P13_POC.chatroom;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Entity
public class ChatRoom {

	@Id
	private String id;
	private String chatId;
	private String senderId;
	private String recipientId;
}
