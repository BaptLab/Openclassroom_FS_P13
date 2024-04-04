import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { RegisterRequest } from '../../interface/registerRequest.interface';
import { LoginRequest } from '../../interface/loginRequest.interface';
import { AuthSuccess } from '../../interface/authSuccess.interface';
import { UserService } from '../../services/httpRequests/user.http.service';
import { SessionService } from '../../services/session/session.service';
import { User } from '../../interface/uesr.interface';
import { switchMap, tap } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  registrationRequest: RegisterRequest = {
    lastName: '',
    firstName: '',
    birthDate: null,
    address: '',
    email: '',
    password: '',
  };

  constructor(
    private authService: AuthService,
    private router: Router,
    private userService: UserService,
    private sessionsService: SessionService
  ) {}

  login(formData: LoginRequest): void {
    const loginRequest: LoginRequest = {
      email: formData.email,
      password: formData.password,
    };

    this.authService
      .login(loginRequest.email, loginRequest.password)
      .pipe(
        tap((authSuccess: AuthSuccess) => {
          console.log('Login successful !', authSuccess);
          localStorage.setItem('user_id', authSuccess.id.toString());
          localStorage.setItem('user_token', authSuccess.token);
        }),
        switchMap((authSuccess: AuthSuccess) =>
          this.userService.getUser(authSuccess.id.toString())
        ),
        tap((user: User) => {
          this.sessionsService.logIn(user);
          this.router.navigate(['/chatroom']);
        })
      )
      .subscribe(
        () => {},
        (error) => {
          // Handle login error
          console.error('Login error', error);
        }
      );
  }

  register(formData: RegisterRequest) {
    console.log(formData);
    this.authService.register(formData).subscribe((registered) => {
      if (registered) {
        // Handle successful registration (e.g., redirect to login page)
        this.router.navigateByUrl('/login');
      } else {
        // Handle registration failure (e.g., show error message)
      }
    });
  }
}
