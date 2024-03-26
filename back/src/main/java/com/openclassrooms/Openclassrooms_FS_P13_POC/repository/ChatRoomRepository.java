package com.openclassrooms.Openclassrooms_FS_P13_POC.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.Openclassrooms_FS_P13_POC.chatroom.ChatRoom;


public interface ChatRoomRepository extends JpaRepository<ChatRoom, String>{

	Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);

}
