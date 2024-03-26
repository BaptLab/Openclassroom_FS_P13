import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    console.log(this.email, this.password);
    this.authService.login(this.email, this.password).subscribe((loggedIn) => {
      if (loggedIn) {
        this.router.navigateByUrl('/chatroom');
      } else {
        // Handle login failure (e.g., show error message)
      }
    });
  }
}
