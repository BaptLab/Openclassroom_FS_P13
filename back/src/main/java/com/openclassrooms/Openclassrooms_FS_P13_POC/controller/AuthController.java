package com.openclassrooms.Openclassrooms_FS_P13_POC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.Openclassrooms_FS_P13_POC.models.User;
import com.openclassrooms.Openclassrooms_FS_P13_POC.payloads.LoginRequest;
import com.openclassrooms.Openclassrooms_FS_P13_POC.payloads.LoginResponse;
import com.openclassrooms.Openclassrooms_FS_P13_POC.payloads.MessageResponse;
import com.openclassrooms.Openclassrooms_FS_P13_POC.payloads.RegisterRequest;
import com.openclassrooms.Openclassrooms_FS_P13_POC.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		try {
			User userBasedOnEmail = userService.findByEmail(loginRequest.getEmail());
			System.out.println(loginRequest.getEmail());
			if (userBasedOnEmail == null) {
				User userBasedOnUsername = userService.findByUsername(loginRequest.getEmail());
				if (userBasedOnUsername == null) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
				} else {
					LoginResponse loginResponse = userService.loginUser(loginRequest, userBasedOnUsername);
					return ResponseEntity.ok(loginResponse);
				}
			} else {
				LoginResponse loginResponse = userService.loginUser(loginRequest, userBasedOnEmail);
				return ResponseEntity.ok(loginResponse);
			}
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid credentials format");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
		try {

			User user = userService.registerRequestToUser(registerRequest);
			userService.save(user);
			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid registration request format");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
		}
	}
}