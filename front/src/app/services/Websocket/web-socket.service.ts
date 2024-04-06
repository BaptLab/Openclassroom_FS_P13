import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { CompatClient, Frame, Stomp } from '@stomp/stompjs';
import { ChatMessage } from '../../interface/chatMessage.interface';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private stompClient!: CompatClient;
  private connectionSubject: Subject<void> = new Subject<void>();
  currentUserId: string = '';

  constructor() {
    this.currentUserId = localStorage.getItem('user_id') || '';
  }

  connect(): void {
    const socket = new WebSocket('ws://localhost:8080/ws');
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, () => {
      console.log('Connected to WebSocket');
      // Trigger the connect endpoint after connection is established
      this.stompClient.send('/app/user/connect', {}, this.currentUserId);
      this.connectionSubject.next(); // Notify that the connection is established
    });
  }

  disconnect(): void {
    console.log('Disconnecting user:', this.currentUserId);
    this.stompClient.send('/app/user/disconnect', {}, this.currentUserId);
  }

  subscribeToConnectedUsers(): Observable<string[]> {
    return new Observable((observer) => {
      // Subscribe only when the connection is established
      this.connectionSubject.subscribe(() => {
        this.stompClient.subscribe('/topic/connectedUsers', (message: any) => {
          console.log('Received connected users:', message);
          observer.next(JSON.parse(message.body));
        });
      });
    });
  }

  sendMessage(message: ChatMessage): void {
    console.log('sending... ' + '/app/chatroom', ' + ', message);
    const messageJson = JSON.stringify(message);
    this.stompClient.send('/app/chatroom', {}, messageJson);
  }

  subscribeToTopic(topic: string): Observable<ChatMessage> {
    return new Observable((observer) => {
      // Wait for the connection to be established before subscribing to topics
      this.connectionSubject.subscribe(() => {
        this.stompClient.subscribe(topic, (message: Frame) => {
          const chatMessage: ChatMessage = JSON.parse(message.body);
          console.log('Received message:', chatMessage);
          observer.next(chatMessage);
        });
      });
    });
  }
}
