import { Component, OnInit } from '@angular/core';
import { WebSocketService } from '../../services/Websocket/web-socket.service';
import { ChatMessage } from '../../interface/chatMessage.interface';

@Component({
  selector: 'app-chatroom',
  templateUrl: './chatroom.component.html',
  styleUrls: ['./chatroom.component.css'],
})
export class ChatroomComponent implements OnInit {
  messageToSend!: string;
  messages: ChatMessage[] = [];
  connectedUsers: string[] = [];
  currentUserId: string | null = '';
  messageText: string = '';

  constructor(private webSocketService: WebSocketService) {
    this.currentUserId = localStorage.getItem('user_id');
  }

  ngOnInit(): void {
    this.webSocketService.connect(this.currentUserId);

    // Subscribe to the 'chatroom' topic
    this.webSocketService
      .subscribeToTopic('/topic/chatroom')
      .subscribe((message: ChatMessage) => {
        this.messages.push(message);
      });

    // Subscribe to the 'connectedUsers' topic
    this.webSocketService
      .subscribeToConnectedUsers()
      .subscribe((users: string[]) => {
        this.connectedUsers = users;
      });
  }

  sendMessage(): void {
    const chatMessage: ChatMessage = {
      message: this.messageToSend,
      user: this.currentUserId,
    };
    // Pass ChatMessage object
    this.webSocketService.sendMessage(chatMessage);
    this.messageToSend = ''; // Clear the input field after sending message
  }

  logout(): void {
    console.log(this.connectedUsers);

    console.log('code de logout method pls');
  }
}
