import { Component, OnInit } from '@angular/core';
import { SessionService } from './services/session/session.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  constructor(
    private sessionsService: SessionService,
    private router: Router
  ) {}
  title = 'Your Car Your Way';

  token: string | null = '';

  ngOnInit(): void {
    this.token = localStorage.getItem('user_token');
    if (this.token) {
      if (this.checkForTokenExpiration(this.token)) {
        this.sessionsService.isLogged = true;
        console.log('You are still connected!');
        this.router.navigate(['/chatroom']);
      } else {
        this.sessionsService.isLogged = false;
        console.log('Session expired, connect again!');
        localStorage.clear();
        this.router.navigate(['/login']);
      }
    } else {
      console.log('No token found, please log in.');
      this.router.navigate(['/login']);
    }
  }

  checkForTokenExpiration(token: string | null): boolean {
    if (token) {
      const expiry = JSON.parse(atob(token.split('.')[1])).exp;
      const isTokenValid = Math.floor(new Date().getTime() / 1000) < expiry;
      return isTokenValid;
    }
    return false;
  }

  ngOnDestroy(): void {}
}
