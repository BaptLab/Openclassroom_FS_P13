import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { filter, Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ChatService {
  private socket$: WebSocketSubject<any> | null = null;
  private connectionStatus$: Subject<boolean> = new Subject<boolean>();

  constructor() {}

  // Function to connect to the WebSocket server
  connectToSocket(): Observable<boolean> {
    console.log('Trying to connect to the socket...');
    this.socket$ = webSocket('ws://localhost:8080/ws'); // WebSocket server URL
    if (this.socket$) {
      console.log('Connected to socket !');
      this.connectionStatus$.next(true); // Notify subscribers that connection is successful
    } else {
      this.connectionStatus$.next(false); // Notify subscribers that connection failed
    }
    return this.connectionStatus$.asObservable();
  }

  // Function to subscribe to user-related updates
  subscribeToUserUpdates(): Observable<any> {
    return this.socket$
      ? this.socket$
          .asObservable()
          .pipe(filter((message) => message.type === 'userData'))
      : new Observable(); // Return empty observable if socket is null
  }

  // Function to connect to a specific channel
  connectToChannel(channel: string): Observable<any> {
    return this.socket$
      ? this.socket$
          .asObservable()
          .pipe(filter((message) => message.channel === channel))
      : new Observable(); // Return empty observable if socket is null
  }

  // Function to send a message
  sendMessage(message: string): void {
    if (this.socket$) {
      this.socket$.next({ content: message }); // Send message to WebSocket server
    }
  }
}
