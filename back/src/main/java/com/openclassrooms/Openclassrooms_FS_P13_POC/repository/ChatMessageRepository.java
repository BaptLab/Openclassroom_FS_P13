package com.openclassrooms.Openclassrooms_FS_P13_POC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.Openclassrooms_FS_P13_POC.models.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
	List<ChatMessage> findByChatId(String s);
}
