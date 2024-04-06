import { Component, OnInit } from '@angular/core';
import { WebSocketService } from '../../services/Websocket/web-socket.service';
import { ChatMessage } from '../../interface/chatMessage.interface';
import { Router } from '@angular/router';
import { UserService } from '../../services/httpRequests/user.http.service';

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
  userName: String = '';

  constructor(
    private webSocketService: WebSocketService,
    private router: Router,
    private userService: UserService
  ) {
    this.currentUserId = localStorage.getItem('user_id');
  }

  ngOnInit(): void {
    this.webSocketService.connect();

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

    // Fetch and subscribe to user's name
    this.userService
      .getUserName(this.currentUserId)
      .subscribe((name: String) => {
        console.log(name, ': name');
        this.userName = name;
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
    this.webSocketService.disconnect();
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
