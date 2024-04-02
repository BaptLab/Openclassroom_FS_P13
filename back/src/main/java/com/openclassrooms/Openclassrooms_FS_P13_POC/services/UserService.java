package com.openclassrooms.Openclassrooms_FS_P13_POC.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.openclassrooms.Openclassrooms_FS_P13_POC.DTOs.UserDTO;
import com.openclassrooms.Openclassrooms_FS_P13_POC.models.User;
import com.openclassrooms.Openclassrooms_FS_P13_POC.payloads.LoginRequest;
import com.openclassrooms.Openclassrooms_FS_P13_POC.payloads.LoginResponse;
import com.openclassrooms.Openclassrooms_FS_P13_POC.payloads.RegisterRequest;
import com.openclassrooms.Openclassrooms_FS_P13_POC.repository.UserRepository;
import com.openclassrooms.Openclassrooms_FS_P13_POC.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	static private JwtUtils jwtUtils;

	public User authenticate(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	public User saveUser(User user) {
		user.setStatus("Online");
		return userRepository.save(user);
	}

	public User disconnect(User user) {
		var storedUser = userRepository.findById(user.getId()).orElse(null);
		if (storedUser != null) {
			storedUser.setStatus("Offline");
			return userRepository.save(storedUser);
		}
		return storedUser;
	}

	public List<User> findConnectedUser() {
		return userRepository.findAllByStatus("Online");
	}

	public User findById(Long id) {
		return this.userRepository.findById(id).orElse(null);
	}

	public void delete(Long id) {
		this.userRepository.deleteById(id);
	}

	public User save(User user) {
		return this.userRepository.save(user);
	}

	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByUsername(String firstName) {
		return userRepository.findByFirstName(firstName);
	}

	public UserDTO userToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(user.getEmail());
		userDTO.setUsername(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setBirthDate(user.getBirthDate());
		return userDTO;
	}

	public LoginResponse loginUser(LoginRequest loginRequest, User user) {
		if (JwtUtils.isPwdMatching(loginRequest, user)) {
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),
					user.getPassword());
			String token = JwtUtils.generateToken(authentication);
			LoginResponse loginResponse = new LoginResponse(token, user.getFirstName(), user.getEmail(), user.getId());
			return loginResponse;
		} else {
			return null;
		}
	}

	public boolean isEmailValid(String email) {
		return email != null && email.matches("\\S+@\\S+\\.\\S+");
	}

	public boolean isPasswordValid(String password) {
		return password != null
				&& password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
	}

	public boolean isUsernameValid(String username) {
		return username != null && username.length() >= 8;
	}

	 public User registerRequestToUser(RegisterRequest registerRequest) {
	        User user = new User();
	        user.setEmail(registerRequest.getEmail());
	        String encryptedPwd = JwtUtils.pwdEncoder(registerRequest.getPassword());
	        user.setPassword(encryptedPwd);
	        user.setLastName(registerRequest.getLastName());
	        user.setFirstName(registerRequest.getFirstName());
	        user.setBirthDate(registerRequest.getBirthDate());
	        user.setAddress(registerRequest.getAddress());
	        return user;
	    }

}
