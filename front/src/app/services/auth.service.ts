import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = 'http://localhost:8080'; // Change the port to the one your backend is running on

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    // Send login request to backend
    return this.http.post<any>(`${this.baseUrl}/login`, {
      email,
      password,
    });
  }

  getUserData(): Observable<any> {
    // Retrieve user data (e.g., connected users, current user) from backend
    return this.http.get<any>(`${this.baseUrl}/userData`);
  }

  logout(): void {
    // Perform logout action (e.g., clear local storage, redirect to login page)
    // ...
  }
}
