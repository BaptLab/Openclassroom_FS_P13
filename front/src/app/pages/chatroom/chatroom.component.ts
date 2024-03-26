import { Component } from '@angular/core';
import { ChatService } from '../../services/chat.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-chatroom',
  templateUrl: './chatroom.component.html',
  styleUrls: ['./chatroom.component.css'],
})
export class ChatroomComponent {
  connectedUsers: string = '';
  currentUser: string = '';
  messages: string[] = [];
  messageText: string = '';

  constructor(
    private chatService: ChatService,
    private authService: AuthService
  ) {
    this.connectedUsers = 'Loading...';
    this.currentUser = 'Loading...';

    // Connect to WebSocket server
    this.chatService.connect();
    // Subscribe to messages
    this.chatService.messages.subscribe((message: string) => {
      this.messages.push(message);
    });

    // Fetch connected users and current user's name
    this.authService.getUserData().subscribe((userData) => {
      this.connectedUsers = userData.connectedUsers.join(', ');
      this.currentUser = userData.currentUser;
    });
  }

  sendMessage(): void {
    if (this.messageText) {
      // Send message to WebSocket server
      this.chatService.sendMessage(this.messageText);
      // Clear message input
      this.messageText = '';
    }
  }

  logout(): void {
    // Perform logout action (e.g., call logout method in AuthService)
    this.authService.logout();
  }
}
