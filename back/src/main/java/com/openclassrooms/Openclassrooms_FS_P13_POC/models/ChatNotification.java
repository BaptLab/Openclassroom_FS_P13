package com.openclassrooms.Openclassrooms_FS_P13_POC.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatNotification {
	
	private Long id;
	private String senderId;
	private String recipientId;
	private String content;
}
