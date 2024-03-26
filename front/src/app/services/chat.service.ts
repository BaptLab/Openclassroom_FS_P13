import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';

@Injectable({
  providedIn: 'root',
})
export class ChatService {
  private socket$: WebSocketSubject<any> | null = null;

  constructor() {}

  connect(): void {
    this.socket$ = webSocket('ws://localhost:8080/ws'); // WebSocket server URL
  }

  sendMessage(message: string): void {
    if (this.socket$) {
      this.socket$.next({ content: message }); // Send message to WebSocket server
    }
  }

  get messages() {
    if (!this.socket$) {
      throw new Error('WebSocket connection not established.');
    }
    return this.socket$.asObservable();
  }
}
