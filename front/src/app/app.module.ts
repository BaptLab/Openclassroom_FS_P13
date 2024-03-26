import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';

import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field'; // Add this import
import { ChatroomComponent } from './pages/chatroom/chatroom.component';

@NgModule({
  declarations: [AppComponent, LoginComponent, ChatroomComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    MatFormFieldModule,
    HttpClientModule,
    BrowserAnimationsModule, // Include MatFormFieldModule here
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
