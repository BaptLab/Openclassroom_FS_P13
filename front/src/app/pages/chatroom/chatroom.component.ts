// chatroom.component.ts
import { Component, OnInit } from '@angular/core';
import { ChatService } from '../../services/chat.service';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { UserService } from '../../services/httpRequests/user.http.service';

@Component({
  selector: 'app-chatroom',
  templateUrl: './chatroom.component.html',
  styleUrls: ['./chatroom.component.css'],
})
export class ChatroomComponent implements OnInit {
  connectedUsers: string = 'Loading...';
  currentUser: string = 'Loading...';
  messages: string[] = [];
  messageText: string = '';
  isSocketConnected: boolean = false;

  constructor(
    private chatService: ChatService,
    private authService: AuthService,
    private route: Router,
    private userHttpService: UserService
  ) {}

  ngOnInit(): void {
    // Connect to WebSocket server
    this.chatService.connectToSocket().subscribe((status: boolean) => {
      this.isSocketConnected = status;
      if (status) {
        // Fetch connected users and current user's name
        this.authService.getUserData().subscribe((userData: any) => {
          this.connectedUsers = userData.connectedUsers.join(', ');
          this.currentUser = userData.currentUser;

          // Send username to backend
          this.userHttpService.sendUsername(this.currentUser).subscribe(
            () => {
              console.log('Username sent to backend successfully.');
            },
            (error) => {
              console.error('Error sending username to backend:', error);
            }
          );

          // Subscribe to messages
          this.subscribeToMessages();
        });
      }
    });
  }

  // Subscribe to messages
  subscribeToMessages(): void {
    this.chatService
      .connectToChannel('chatroom')
      .subscribe((message: string) => {
        this.messages.push(message);
      });
  }

  // Function to send a message
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
    this.route.navigate(['/login']);
  }
}
