import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../interface/uesr.interface';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private httpClient: HttpClient) {}

  private baseUrl = 'http://localhost:8080';
  private pathService = '/api/user';

  public getUser(userId: string | null): Observable<User> {
    return this.httpClient.get<User>(
      `${this.baseUrl}${this.pathService}/${userId}`
    );
  }

  public getUserName(userId: string | null): Observable<String> {
    return this.httpClient.get<String>(
      `${this.baseUrl}${this.pathService}/${userId}/name`
    );
  }

  public sendUsername(username: string): Observable<any> {
    const url = `${this.baseUrl}${this.pathService}/username`;
    return this.httpClient.post(url, { username });
  }
}
