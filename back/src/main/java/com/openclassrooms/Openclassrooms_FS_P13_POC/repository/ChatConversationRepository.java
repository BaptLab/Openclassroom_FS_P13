package com.openclassrooms.Openclassrooms_FS_P13_POC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.Openclassrooms_FS_P13_POC.models.ChatConversation;


@Repository
public interface ChatConversationRepository extends JpaRepository<ChatConversation, Long>{

}
