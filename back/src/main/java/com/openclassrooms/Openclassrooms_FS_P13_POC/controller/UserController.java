package com.openclassrooms.Openclassrooms_FS_P13_POC.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.openclassrooms.Openclassrooms_FS_P13_POC.models.User;
import com.openclassrooms.Openclassrooms_FS_P13_POC.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from http://localhost:4200
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User loginUser) {
        User authenticatedUser = userService.authenticate(loginUser.getEmail(), loginUser.getPassword());
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
	
	@MessageMapping("/user.addUser")
	@SendTo("/user/topic")
	public User addUser(@Payload User user) {
		User savedUser = userService.saveUser(user);
		return savedUser;
	}
	
	@MessageMapping("/user.disconnectUser")
	@SendTo("/user/topic")
	public User disconnect(@Payload User user) {
		User disconnectedUser = userService.disconnect(user);
		userService.disconnect(disconnectedUser);
		return disconnectedUser;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> findConnectedUsers(){
		return ResponseEntity.ok(userService.findConnectedUser());
	}
}
