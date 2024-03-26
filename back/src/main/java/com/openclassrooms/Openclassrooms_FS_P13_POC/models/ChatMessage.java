package com.openclassrooms.Openclassrooms_FS_P13_POC.models;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "CHAT_MESSAGE")
@Data
@Getter
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String chatId;
    private String senderId ;
    private String recipientId;
    

    @ManyToOne
    @JoinColumn(name = "conversation_id") // Name of the column that references the conversation
    private ChatConversation conversation;

    @ManyToOne
    @JoinColumn(name = "user_id") // Name of the column that references the user
    private User user;

    private String userFirstName; // Derived from the associated user
    private String contentChatMsg;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
