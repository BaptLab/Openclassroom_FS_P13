package com.openclassrooms.Openclassrooms_FS_P13_POC.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.Openclassrooms_FS_P13_POC.models.User;
import com.openclassrooms.Openclassrooms_FS_P13_POC.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
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
		var storedUser = userRepository.findById(user.getId())
				.orElse(null);
		if(storedUser != null) {
			storedUser.setStatus("Offline");
			return userRepository.save(storedUser);
		}
		return storedUser;
	}
	
	public List<User> findConnectedUser(){
		return userRepository.findAllByStatus("Online");
	}
}
